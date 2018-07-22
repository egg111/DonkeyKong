/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.UserDao;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Users;
import util.DateUtils;

/**
 *
 * @author hasee
 */
@Service
public class UsersServiceImpl implements UsersService{
    @Autowired
    UserDao userDao;
    
    @Transactional(
            //what type of propagation is needed for this type of transaction
            propagation = Propagation.REQUIRED,
            //is this transaction read only
            readOnly = true,
            timeout = 30,
            //what are the rollback rules(When the transaction should roll back)
            rollbackFor = {SQLException.class, IOException.class, RuntimeException.class},
            //roll back the reansaction if the method gives any of these exception
            //isolation level
            isolation = Isolation.READ_COMMITTED
    )

    @Override
    public Users login(String uid,String upass) {
        Users user = userDao.getAUser(uid);
        if(user !=null && user.getPassword().equals(upass)){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public void saveUser(Users user) {
        userDao.save(user);
    }

    @Override
    public void updateUser(Users user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(String uid) {
        userDao.delete(uid);
    }

    @Override
    public ArrayList<Users> getUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public Users getAUser(String uid) {
//        return userDao.login(uid);
        Users user= userDao.getAUser(uid);
        if(user !=null && user.getUserId().equals(uid)){
            return user;
        }
        return null;
    }

    @Override
    public void changePass(Users user) {
        userDao.updatePass(user);
    }

    @Override
    public void updatePic(Users user) {
        userDao.updatePic(user);
    }

    @Override
    public void joinTravel(String uid, String tid) {
        userDao.joinTravel(uid, tid);
    }

    @Override
    public String checkExist(Users user) {
        return userDao.checkExist(user);
    }

    @Override
    public boolean checkJoinTravel(String uid, String tid) {
        if(userDao.checkJoinTravel(uid, tid))
            return true;
        else
            return false;
    }


    
    
}
