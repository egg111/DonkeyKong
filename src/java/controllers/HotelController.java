/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.HotelDaoImpl;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Areas;
import pojo.Hotel;
import pojo.HotelComment;
import pojo.Report;
import pojo.Users;
import services.AreasService;
import services.HotelService;
import services.UsersService;
import util.DataUtils;
import util.DateUtils;
import util.IDUtils;

/**
 *
 * @author hasee
 */
@Controller
@RequestMapping(value = "hotel")
public class HotelController {

    @Autowired
    HotelService hs;
    @Autowired
    UsersService us;
    @Autowired
    AreasService as;

    String hotelId;
    String roomId;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addHotel(Hotel hotel, Model model) {
        Areas area = as.getAreaByName(hotel.getAreaName());
        
        if(area == null){
            area = new Areas();
            area.setAreaName(hotel.getAreaName());           
            area.setAreaId(IDUtils.getUUID());           
            as.addAreas(area);
        }
        hotel.setHotelId(IDUtils.getUUID());
        hotel.setAreas(area);
        hs.addHotel(hotel);
        model.addAttribute("hotelList", hs.getAllHotel());
        return "manager/hotelManage";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteHotel(String hid) {
        hs.deleteHotel(hid);
    }
    @RequestMapping(value = "/delBook", method = RequestMethod.POST)
    @ResponseBody
    public void delBook(String bid) {
        hs.delBook(bid);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editHotel(Hotel hotel, Model model) {
        Areas area = as.getAreaByName(hotel.getAreaName());
        if(area == null){
            area = new Areas();
            area.setAreaName(hotel.getAreaName());
            area.setAreaId(IDUtils.getUUID());
            as.addAreas(area);
        }
        hotel.setAreas(area);
        hs.updateHotel(hotel);
        model.addAttribute("hotelList", hs.getAllHotel());
        return "manager/hotelManage";
    }

    @RequestMapping(value = "showAll", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Hotel> showAllHotel(Model model) {
        model.addAttribute("hotelList", hs.getAllHotel());
        return hs.getAllHotel();
    }

    @RequestMapping(value = "/getAHotel", method = RequestMethod.GET)
    public String getAHotel(String hid, Model model) {
        Hotel hotel = hs.getAHotel(hid);
        model.addAttribute("hotel", hotel);
        hotel.setHotelRooms(hs.getAllRoomsByHotel(hotel));
        ArrayList<HotelComment> list = hs.getAllComments(hotel);
        for (HotelComment comments : list) {
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "hotelDetail";
    }

    @RequestMapping(value = "/commentedit", method = RequestMethod.POST)
    public String editNoteComment(String comment, String hotelId, Model model, HttpSession session) {
        Hotel hotel = hs.getAHotel(hotelId);
        Users user = (Users) session.getAttribute("CURRENT_USER");
        HotelComment hotelComment = new HotelComment(IDUtils.getUUID(), hotel, user, comment, DateUtils.getTimestamp());
        hs.addHotelComment(hotelComment);
        ArrayList<HotelComment> list = hs.getAllComments(hotel);
        for (HotelComment comments : list) {
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "scenicDetail";
    }

    @RequestMapping(value = "/sortByLike", method = RequestMethod.GET)
    public String editNoteComment(Model model, HttpSession session) {
        model.addAttribute("hotelList", hs.sortByLike());
        for (int i = 0; i < hs.sortByLike().size(); i++) {
            System.out.println(hs.sortByLike().get(i).getHotelLike() + "------like");
        }
        return "hotelPage";
    }

    @RequestMapping(value = "/setHotelId", method = RequestMethod.POST)
    public String setHotelId(String hid, Model model) {
        hotelId = hid;
        return "hotelDetail";
    }

    @RequestMapping(value = "/setRoomId", method = RequestMethod.POST)
    public String setRoomId(String rid, Model model) {
        roomId = rid;
        return "hotelDetail";
    }

    //hid, uid, rid, ftime, ttime
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String bookRoom(String hid, String uid, String rid, String ftime, String ttime, Model model, HttpSession session) {
        try {
            Users u = (Users) session.getAttribute("CURRENT_USER");
            if(u==null){
                return "fail";
            }
            else{
                uid = u.getUserId();
                StringBuffer fromtime1 = new StringBuffer(ftime);
            fromtime1.append(" 12:00:00");
            String fromtime = new String(fromtime1);

            StringBuffer totime1 = new StringBuffer(ttime);
            totime1.append(" 12:00:00");
            String totime = new String(totime1);

            Timestamp from = DateUtils.StringToTimestamp(fromtime);
            Timestamp to = DateUtils.StringToTimestamp(totime);
            hid = hotelId;
            rid = roomId;
            if(hs.checkBook(uid, hid)){
                hs.bookRoom(hid, uid, rid, from, to);
                return "hotelDetail";
            }
            else{
                return "fail";
            }
            }
            
            
        } catch (ParseException ex) {
            Logger.getLogger(HotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @RequestMapping(value = "/changePic",method=RequestMethod.POST)
    public @ResponseBody String changeScenicPic(String source, String hotelId, Model model){
        
        System.out.println(source);
        String p = this.getClass().getResource("").getPath();
        String path = p.substring(0,p.indexOf("build"));
        Hotel hotel = hs.getAHotel(hotelId);
        if(hotel.getHotelImg()!= null && hotel.getHotelImg().length() > 32){
            DataUtils.deleteImg(path+"web/resource/imags/hotel/"+hotel.getHotelImg());
        }
        hotel.setHotelImg(DataUtils.saveImg(source, path+"web/resource/imags/hotel/"));
        hs.updatePic(hotel);
        return "manager/hotelManage";
    }
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public @ResponseBody void hotelLike(String like, String hotelId, Model model){
        int liked  = Integer.parseInt(like);
        Hotel hotel = hs.getAHotel(hotelId);
        hotel.setHotelLike(++liked);
        hs.updateHotel(hotel);
        model.addAttribute("h", hotel);
    }
    
    @RequestMapping(value = "/hotelReport", method = RequestMethod.POST)
    public @ResponseBody String hotelReport(String content, String hotelId, Model model, HttpSession session){
        Hotel hotel = hs.getAHotel(hotelId);
        Report report = new Report();
        report.setReportId(IDUtils.getUUID());
        report.setUser((Users) session.getAttribute("CURRENT_USER"));
        report.setHotel(hotel);
        report.setReportContent(content);
        report.setReportTime(DateUtils.getTimestamp());
        if(hs.findReport(report.getUser(), report.getHotel())){
            model.addAttribute("reportResult", "Your have already reported.");
            return "hotelDetail";
        }
        hs.addHotelReport(report);
        model.addAttribute("reportResult", "Report successful.");
        return "hotelDetail";
    }
    
    @RequestMapping(value = "/commentReport", method = RequestMethod.POST)
    public @ResponseBody String commentReport(String content, String commentId, Model model, HttpSession session){
        HotelComment comment = hs.getHotelCommentById(commentId);
        Report report = new Report();
        report.setReportId(IDUtils.getUUID());
        report.setUser((Users) session.getAttribute("CURRENT_USER"));
        report.setHotelComment(comment);
        report.setReportContent(content);
        report.setReportTime(DateUtils.getTimestamp());
        if(hs.findReportComment(report.getUser(), report.getHotelComment())){
            model.addAttribute("reportResult", "Your have already reported.");
            return "hotelDetail";
        }
        hs.addHotelReport(report);
        model.addAttribute("reportResult", "Report successful.");
        return "hotelDetail";
    }
}
