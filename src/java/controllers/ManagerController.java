/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pojo.Admin;
import pojo.Users;
import services.ManagerService;
import util.MD5Utils;

/**
 *
 * @author hasee
 */
@Controller
@RequestMapping(value="/admin")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    //login function
    @RequestMapping(value="/login" ,method=RequestMethod.POST)
    public String loginAdmin(String mid,String mpass,Model model,Admin admin){

        mpass = MD5Utils.md5(mpass);
        admin = managerService.login(mid, mpass);

        if(admin !=null){
            model.addAttribute("admin",admin);
            return "manager/changePass";
        }else{
            return "fail";
        }
    }
    
    @RequestMapping(value = "/changePass",method = RequestMethod.POST)
    public String changePass(Admin admin){
        String smi = MD5Utils.md5(admin.getPassword());
        admin.setPassword(smi);
        managerService.update(admin);       
        return "manager/userManage";
    }
    @RequestMapping(value = "/freeze",method = RequestMethod.POST)
    public String freezeUser(String uid){
        managerService.freezeUser(uid, 1);
        return "manager/userManage";
    }
    @RequestMapping(value = "/unfreeze",method = RequestMethod.POST)
    public String unfreezeUser(String uid){
        managerService.freezeUser(uid, 0);
        return "manager/userManage";
    }
}
