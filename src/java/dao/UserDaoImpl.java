/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Repository;
import pojo.Tour;
import pojo.TourJoin;
import pojo.Users;
import util.DateUtils;
import util.IDUtils;

/**
 *
 * @author hasee
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory factory;

    @Override
    public Users getAUser(String uid) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Users temp = (Users) session.get(Users.class, uid);
        trans.commit();
        session.close();
        return temp;
    }

    @Override
    public void save(Users user) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        Users user2 = user;
        user2.setLevel(0);
        user2.setFreeze(0);
        session.save(user);
        trans.commit();
        session.close();
    }

    @Override
    public void update(Users user) {
        System.out.println(user.getEmail() + "  " + user.getTel());
        Session session = factory.openSession();
        String hql = "update Users user set user.tel='" + user.getTel() + "', user.email='" + user.getEmail() + "' where user.userId='" + user.getUserId() + "'";
        Transaction trans = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.executeUpdate();
        trans.commit();
        session.close();
    }

    @Override
    public void delete(String uid) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        session.delete(getAUser(uid));
        trans.commit();
        session.close();
    }

    @Override
    public ArrayList<Users> getAllUsers() {
        Session session = factory.openSession();
        String hql = "from Users";
        ArrayList<Users> list = (ArrayList<Users>) session.createQuery(hql).list();
        System.out.println("==========User>" + list.size());
        session.close();
        return list;
    }

    @Override
    public void updatePass(Users user) {
        Session session = factory.openSession();
        String hql = "update Users user set user.password='" + user.getPassword() + "' where userId='" + user.getUserId() + "'";
        Transaction trans = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.executeUpdate();
        trans.commit();
        session.close();
    }

    @Override
    public void updatePic(Users user) {
        Session session = factory.openSession();
        String hql = "update Users set userImg=?, userUpdateTime=?  where userId=?";
        Transaction trans = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setString(0, user.getUserImg());
        query.setTimestamp(1, user.getUserUpdateTime());
        query.setString(2, user.getUserId());
        query.executeUpdate();
        trans.commit();
        session.close();
    }

    @Override
    public void joinTravel(String uid, String tid) {
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();
        TourJoin tj = new TourJoin();
        Tour t1 = getTourById(tid);
        Users u1 = getUserById(uid);
        tj.setTjId(IDUtils.getUUID());
        tj.setTour(t1);
        tj.setUsers(u1);
        if(t1.getIsApprove()==1){
            session.save(tj);
        }
        trans.commit();
        session.close();
    }

    public Tour getTourById(String tid) {
        String hqltour = "from Tour where tourId=?";
        Session session = factory.openSession();
        Query querytour = session.createQuery(hqltour).setString(0, tid);
        ArrayList<Tour> listtouri = (ArrayList<Tour>) querytour.list();
        session.close();
        return listtouri.get(0);
    }

    public Users getUserById(String uid) {
        String hqltour = "from Users where userId=?";
        Session session = factory.openSession();
        Query querytour = session.createQuery(hqltour).setString(0, uid);
        ArrayList<Users> listtouri = (ArrayList<Users>) querytour.list();
        session.close();
        return listtouri.get(0);
    }

    @Override
    public String checkExist(Users user) {
        String message = "";
        Session session = factory.openSession();
        Users tmp1 = (Users) session.createQuery("from Users where userId = ?").setString(0, user.getUserId()).uniqueResult();
        if (tmp1 != null) {
            message += "ID existed!";
            System.out.println(message);
        }
        Users tmp2 = (Users) session.createQuery("from Users where  email = ?").setString(0, user.getEmail()).uniqueResult();
        if (tmp2 != null) {
            message += " Email existed!";
            System.out.println(message);
        }
        Users tmp3 = (Users) session.createQuery("from Users where  tel = ?").setString(0, user.getTel()).uniqueResult();
        if (tmp3 != null) {
            message += " Phone Number existed!";
            System.out.println(message);
        }
        return message.equals("") ? "NotExist" : message;
    }

    @Override
    public boolean checkJoinTravel(String uid, String tid) {
        Session session = factory.openSession();
        Query q = session.createQuery("from TourJoin where users = ? and tour = ?");
        Users user=getUserById(uid);
        Tour tour=getTourById(tid);
        q.setString(0, user.getUserId());
        q.setString(1, tour.getTourId());
        TourJoin temp = (TourJoin) q.uniqueResult();
        if(temp == null){
            return true;
        }
        return false;
    }
}