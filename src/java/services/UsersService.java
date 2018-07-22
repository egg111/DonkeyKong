/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import pojo.Tour;
import pojo.Users;

/**
 *
 * @author hasee
 */
public interface UsersService {
    Users login(String uid,String upass);
    void saveUser(Users user);
    void updateUser(Users user);
    void deleteUser(String uid);
    void changePass(Users user);
    ArrayList<Users> getUsers();
    Users getAUser(String uid);
    void updatePic(Users user);
    void joinTravel(String uid, String tid);
    boolean checkJoinTravel(String uid, String tid);
    public String checkExist(Users user);
}
