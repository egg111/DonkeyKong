/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Areas;
import pojo.FoodStore;
import pojo.FoodStoreComment;
import pojo.Note;
import pojo.Report;
import pojo.Users;
import services.AreasService;
import services.FoodService;
import services.UsersService;
import util.DataUtils;
import util.DateUtils;
import util.IDUtils;

/**
 *
 * @author hasee
 */
@Controller
@RequestMapping(value = "food")
public class FoodController {
    
    @Autowired
    FoodService fs;
    @Autowired
    UsersService us;
    @Autowired
    AreasService as;

    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public String addFood(FoodStore food,Model model){
        Areas area = as.getAreaByName(food.getAreaName());
        
        if(area == null){
            area = new Areas();
            area.setAreaName(food.getAreaName());            
            area.setAreaId(IDUtils.getUUID());            
            as.addAreas(area);
        }
        food.setFoodStoreId(IDUtils.getUUID());
        food.setAreas(area);
        fs.addFood(food);
        model.addAttribute("foodList", fs.getAllFood());
        return "manager/foodManage";
    }
    
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public void deleteFood(String fid){
        fs.deleteFood(fid);
    }
    
    @RequestMapping(value ="/edit" ,method = RequestMethod.POST)
    public @ResponseBody String editFood(FoodStore food,Model model) throws FileNotFoundException{
        System.out.println("===>"+food.getAreaName());
        Areas area = as.getAreaByName(food.getAreaName());
        if(area == null){
            area = new Areas();
            area.setAreaName(food.getAreaName());
            area.setAreaId(IDUtils.getUUID());
            as.addAreas(area);
        }
        food.setAreas(area);
        fs.updateFood(food);
        model.addAttribute("foodList", fs.getAllFood());
        return "manager/foodManage";
    }
    
    @RequestMapping(value = "showAll",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<FoodStore> showAllFood(Model model){
        model.addAttribute("foodList",fs.getAllFood());
        return fs.getAllFood();
    }
    
    @RequestMapping(value = "getAFood",method = RequestMethod.GET)
    public String getAFood(String fid,Model model){
        FoodStore food = fs.getFoodById(fid);
        model.addAttribute("food", food);
        ArrayList<FoodStoreComment> list = fs.getAllComments(food);
        for(FoodStoreComment comments : list){
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "foodDetail";
    }
    
    
    @RequestMapping(value = "/commentedit", method = RequestMethod.POST)
    public String editNoteComment(String comment, String storeId, Model model, HttpSession session){
        FoodStore store = fs.getFoodById(storeId);
        Users user = (Users) session.getAttribute("CURRENT_USER");
        FoodStoreComment noteComment = new FoodStoreComment(IDUtils.getUUID(),store , user, comment, DateUtils.getTimestamp());
        fs.addStoreComment(noteComment);
        ArrayList<FoodStoreComment> list = fs.getAllComments(store);
        for(FoodStoreComment comments : list){
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "foodDetail";
    }
    //update user's pic
    @RequestMapping(value = "/changePic",method=RequestMethod.POST)
    public @ResponseBody String changeFoodPic(String source, String storeId, Model model){
        System.out.println("=======>"+storeId);
        System.out.println(source);
        String p = this.getClass().getResource("").getPath();
        String path = p.substring(0,p.indexOf("build"));
        FoodStore food = fs.getFoodById(storeId);
        if(food.getStoreImg() != null && food.getStoreImg().length() > 32){
            DataUtils.deleteImg(path+"web/resource/imags/food/"+food.getStoreImg());
        }
        food.setStoreImg(DataUtils.saveImg(source, path+"web/resource/imags/food/"));
        fs.updatePic(food);
        return "manager/foodManage";
    }
    
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public @ResponseBody void foodLike(String like, String foodStoreId, Model model){
        int liked  = Integer.parseInt(like);
        FoodStore food = fs.getFoodById(foodStoreId);
        food.setLiked(++liked);
        fs.updateFood(food);
        model.addAttribute("f",food);
    }
    
    @RequestMapping(value = "/foodReport", method = RequestMethod.POST)
    public @ResponseBody String foodReport(String content, String foodStoreId, Model model, HttpSession session){
        FoodStore food = fs.getFoodById(foodStoreId);
        Report report = new Report();
        report.setReportId(IDUtils.getUUID());
        report.setUser((Users) session.getAttribute("CURRENT_USER"));
        report.setFoodStore(food);
        report.setReportContent(content);
        report.setReportTime(DateUtils.getTimestamp());
        if(fs.findReport(report.getUser(), report.getFoodStore())){
            model.addAttribute("reportResult", "Your have already reported.");
            return "foodDetail";
        }
        fs.addFoodReport(report);
        model.addAttribute("reportResult", "Report successful.");
        return "foodDetail";
    }
    
    @RequestMapping(value = "/commentReport", method = RequestMethod.POST)
    public @ResponseBody String commentReport(String content, String commentId, Model model, HttpSession session){
        FoodStoreComment comment = fs.getFoodStoreCommentById(commentId);
        Report report = new Report();
        report.setReportId(IDUtils.getUUID());
        report.setUser((Users) session.getAttribute("CURRENT_USER"));
        report.setFoodStoreComment(comment);
        report.setReportContent(content);
        report.setReportTime(DateUtils.getTimestamp());
        if(fs.findReportComment(report.getUser(), report.getFoodStoreComment())){
            model.addAttribute("reportResult", "Your have already reported.");
            return "foodDetail";
        }
        fs.addFoodReport(report);
        model.addAttribute("reportResult", "Report successful.");
        return "foodDetail";
    }
}
