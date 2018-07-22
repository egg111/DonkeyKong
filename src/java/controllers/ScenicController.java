/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Areas;
import pojo.Report;
import pojo.Scenic;
import pojo.ScenicComment;
import pojo.Users;
import services.AreasService;
import services.ScenicService;
import services.UsersService;
import util.DataUtils;
import util.DateUtils;
import util.IDUtils;

/**
 *
 * @author hasee
 */
@Controller
@RequestMapping(value = "/scenic")
public class ScenicController {
    
    @Autowired
    ScenicService ss;
    @Autowired
    UsersService us;
    @Autowired
    AreasService as;
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public String addScenic(Scenic scenic,Model model){
        System.out.println("+++++++++++"+scenic.getAreaName());
        Areas area = as.getAreaByName(scenic.getAreaName());
        
        if(area == null){
            area = new Areas();
            area.setAreaName(scenic.getAreaName());            
            area.setAreaId(IDUtils.getUUID());            
            as.addAreas(area);
        }
        scenic.setScenicId(IDUtils.getUUID());
        scenic.setAreas(area);
        ss.addScenic(scenic);
        
        model.addAttribute("scenicList", ss.getAllScenic());
        return "manager/scenicManage";
    }
    
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public void deleteScenic(String sid){
        ss.deleteScenic(sid);
    }
    
    @RequestMapping(value ="/edit" ,method = RequestMethod.POST)
    public @ResponseBody String editScenic(Scenic scenic, Model model) throws FileNotFoundException{
        Areas area = as.getAreaByName(scenic.getAreaName());
        if(area == null){
            area = new Areas();
            area.setAreaName(scenic.getAreaName());
            area.setAreaId(IDUtils.getUUID());
            as.addAreas(area);
        }
        scenic.setAreas(area);
        ss.update(scenic);
        model.addAttribute("scenicList", ss.getAllScenic());
        return "manager/scenicManage";
    }
    
    @RequestMapping(value = "showAll",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Scenic> showAllScenic(Model model){
//        model.addAttribute("scenicList",ss.getAllScenic());
        return ss.getAllScenic();
    }
    
    @RequestMapping(value = "getAScenic",method = RequestMethod.GET)
    public String getAScenic(String sid,Model model){
        Scenic scenic = ss.getScenic(sid);
        model.addAttribute("scenic", scenic);
        ArrayList<ScenicComment> list = ss.getAllComments(scenic);
        for(ScenicComment comments : list){
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "scenicDetail";
    }
    
    @RequestMapping(value = "/commentedit", method = RequestMethod.POST)
    public String editNoteComment(String comment, String scenicId, Model model, HttpSession session){
        Scenic scenic = ss.getScenic(scenicId);
        Users user = (Users) session.getAttribute("CURRENT_USER");
        ScenicComment scenicComment = new ScenicComment(IDUtils.getUUID(),scenic , user, comment, DateUtils.getTimestamp());
        ss.addScenicComment(scenicComment);
        ArrayList<ScenicComment> list = ss.getAllComments(scenic);
        for(ScenicComment comments : list){
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "scenicDetail";
    }
    
    //update user's pic
    @RequestMapping(value = "/changePic",method=RequestMethod.POST)
    public @ResponseBody String changeScenicPic(String source, String scenicId, Model model){
        String p = this.getClass().getResource("").getPath();
        String path = p.substring(0,p.indexOf("build"));
        Scenic scenic = ss.getScenic(scenicId);
        if(scenic.getScenicImg() != null && scenic.getScenicImg().length() > 32){
            DataUtils.deleteImg(path+"web/resource/imags/scenic/"+scenic.getScenicImg());
        }
        scenic.setScenicImg(DataUtils.saveImg(source, path+"web/resource/imags/scenic/"));
        ss.updatePic(scenic);
        return "manager/scenicManage";
    }
    
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public @ResponseBody void scenicLike(String like, String scenicId, Model model){
        int liked  = Integer.parseInt(like);
        Scenic scenic = ss.getScenic(scenicId);
        scenic.setScenicLike(++liked);
        ss.update(scenic);
        model.addAttribute("s", scenic);
    }
    
    @RequestMapping(value = "/commentReport", method = RequestMethod.POST)
    public @ResponseBody String commentReport(String content, String commentId, Model model, HttpSession session){
        ScenicComment comment = ss.getScenicCommentById(commentId);
        Report report = new Report();
        report.setReportId(IDUtils.getUUID());
        report.setUser((Users) session.getAttribute("CURRENT_USER"));
        report.setScenicComment(comment);
        report.setReportContent(content);
        report.setReportTime(DateUtils.getTimestamp());
        
        if(ss.findReportComment(report.getUser(), report.getScenicComment())){
            System.out.println("exist.............");
            model.addAttribute("reportResult", "Your have already reported.");
            return "scenicDetail";
        }
        ss.addNoteReport(report);
        System.out.println("nnnnnnnnnnnnnnnnnnnnnn");
        model.addAttribute("reportResult", "Report successful.");
        return "scenicDetail";
    }
}
