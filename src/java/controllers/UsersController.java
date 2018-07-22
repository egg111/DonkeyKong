/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Users;
import services.UsersService;
import util.DataUtils;
import util.DateUtils;
import util.EmailUtil;
import util.IDUtils;
import util.MD5Utils;

/**
 *
 * @author hasee
 */
@Controller
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    
    //go to the register page
    @RequestMapping(value="/register")
    public String toRegister(){
        return "register";
    }
    
    //register a new user
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(Users user,String password, Model model){
        String message = usersService.checkExist(user);
        if(!message.equals("NotExist")){
            model.addAttribute("message", message);
            return "register";
        }
        else{
            String smi = MD5Utils.md5(password);
            user.setPassword(smi);
            Timestamp time = DateUtils.getTimestamp();
            user.setUserRegistTime(time);
            user.setUserUpdateTime(time);
            usersService.saveUser(user);      
            return "login";
        }
    }
    
    //login function
    @RequestMapping(value="/login" ,method=RequestMethod.POST)
    public String loginUser(String uid,String upass,Model model, HttpSession session){
        upass = MD5Utils.md5(upass);
        Users user = usersService.login(uid, upass);
        if(user !=null && user.getFreeze()==0){
            session.setAttribute("CURRENT_USER", user);
            model.addAttribute("user",user);
            return "user/userDetail";
        }else if(user !=null && user.getFreeze()==1){
            return "freeze";
        }else{
            return "fail";
        }
    }
    
    @RequestMapping(value="/logout" ,method=RequestMethod.POST)
    public String logOut(HttpSession session){
        session.removeAttribute("CURRENT_USER");
        return "login";
    }
    
    //add new user
    @RequestMapping(value="/addUser" ,method=RequestMethod.POST)
    public String addUser(Users user,Model model,String password){
        String smi = MD5Utils.md5(password);
        System.out.println(smi);
        user.setPassword(smi);
        Timestamp time = DateUtils.getTimestamp();
        user.setUserRegistTime(time);
        user.setUserUpdateTime(time);
        usersService.saveUser(user);
        model.addAttribute("userList", usersService.getUsers());
        return "toUserManage";
    }
    
    //delete user
    @RequestMapping(value = "/delUser",method=RequestMethod.POST)
    @ResponseBody
    public void deleteUser(String uid){
        usersService.deleteUser(uid);
    }
    
    //update user
    @RequestMapping(value = "/editUser",method=RequestMethod.POST)
    public String editUser(Users user, Model model){
        String smi = MD5Utils.md5(user.getPassword());
        user.setPassword(smi);
        Timestamp time = DateUtils.getTimestamp();
        user.setUserUpdateTime(time);
        usersService.updateUser(user);
        model.addAttribute("userList", usersService.getUsers());
        return "toUserManage";
    }
    
    
    //show userList
    @RequestMapping(value = "/userList" ,method=RequestMethod.GET)
    @ResponseBody
    public ArrayList<Users> showUserList(Model model){
        model.addAttribute("userList", usersService.getUsers());
        return usersService.getUsers();
    }
    
    //find a user
    @RequestMapping(value="/getAUser",method=RequestMethod.POST)
    public String getAUser(String uid,Model model){
        Users user = usersService.getAUser(uid);
        model.addAttribute("getAUser", user);
        return "manager/uaerManager";
    }
    
    //user change password
    @RequestMapping(value = "/changePass",method=RequestMethod.POST)
    public String changPass(Users user){
        String smi = MD5Utils.md5(user.getPassword());
        user.setPassword(smi);
        usersService.changePass(user);
        return "user/userDetail";
    }
    //user change message
    @RequestMapping(value = "/update",method=RequestMethod.POST)
    public String changeMessage(Users user, HttpSession session){
        session.setAttribute("CURRENT_USER", user);
        user.setUserUpdateTime(DateUtils.getTimestamp());
        usersService.updateUser(user);
        return "user/userDetail";
    }
    
    //update user's pic
    @RequestMapping(value = "/changeUserPic",method=RequestMethod.POST)
    public String changeUserPic(String source, Model model, HttpSession session){
        String p = this.getClass().getResource("").getPath();
        String path = p.substring(0,p.indexOf("build"));
        Users user = (Users) session.getAttribute("CURRENT_USER");
        if(user.getUserImg() != null && user.getUserImg().length() > 32){
            DataUtils.deleteImg(path+"web/resource/imags/user/"+user.getUserImg());
        }
        Timestamp time = DateUtils.getTimestamp();
        user.setUserUpdateTime(time);
        user.setUserImg(DataUtils.saveImg(source, path+"web/resource/imags/user/"));
        usersService.updatePic(user);
        session.setAttribute("CURRENT_USER", user);
        return "user/userDetail";
    }
    
    //join one's travel
    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String joinTravel(String tid, HttpSession session){
        Users u=(Users) session.getAttribute("CURRENT_USER");
        if(u==null){
            return "fail";
        }else{
            String uid=u.getUserId();
            if(usersService.checkJoinTravel(uid, tid)){
                
                usersService.joinTravel(uid, tid);
                return "user/join";
            }
            else{
                System.out.println(usersService.checkJoinTravel(uid, tid)+"----------check");
                return "fail";
            }
        }
    }
    
    //findPassword
    @RequestMapping(value="/findPass", method=RequestMethod.POST)
    public String findPass(String uid, String email){
        if(uid==null || email==null){
            return "fail";
        }else{
            Users u = usersService.getAUser(uid);
            if(u.getPassword()==null || !u.getEmail().equals(email)){
                return "fail";
            }else{
                try {
                    String code=IDUtils.getRandomCode();
                    EmailUtil.send_mail(email,"Mr."+u.getUserId()+", your new password is "+code);
                    u.setPassword(code);
                    changPass(u);
                    return "login";
                } catch (MessagingException ex) {
                    System.out.println("send mail error: "+ex);
                }
            }
            
        }
        return "fail";
    }
}
    
