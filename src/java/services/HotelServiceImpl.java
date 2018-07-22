/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.HotelDao;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Hotel;
import pojo.HotelBook;
import pojo.HotelComment;
import pojo.HotelRoom;
import pojo.Report;
import pojo.ScenicComment;
import pojo.Users;

/**
 *
 * @author hasee
 */
@Service
public class HotelServiceImpl implements HotelService{
    
    @Autowired
    HotelDao hotelDao;
    
    @Override
    public void addHotel(Hotel hotel) {
        hotelDao.insertHotel(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        hotelDao.update(hotel);
    }

    @Override
    public void deleteHotel(String hid) {
        hotelDao.deleteHotel(hid);
    }

    @Override
    public ArrayList<Hotel> getAllHotel() {
        return hotelDao.getAllHotel();
    }

    @Override
    public Hotel getAHotel(String hid) {
        return hotelDao.getHotelById(hid);
    }

    @Override
    public void addHotelComment(HotelComment hotelComment) {
        hotelDao.insertHotelComment(hotelComment);
    }

    @Override
    public ArrayList<HotelComment> getAllComments(Hotel hotel) {
        return hotelDao.getAllComments(hotel);
    }

    @Override
    public Set<HotelRoom> getAllRoomsByHotel(Hotel hotel) {
        return hotelDao.getAllRoomsByHotel(hotel);
    }

    @Override
    public ArrayList<Hotel> sortByLike() {
        return hotelDao.sortByLike();
    }

    @Override
    public void bookRoom(String hid, String uid, String rid, Timestamp ftime, Timestamp ttime) {
        hotelDao.bookRoom(hid, uid, rid, ftime, ttime);
    }

    @Override
    public ArrayList<HotelBook> getAllBookByUserId(String uid) {
        return hotelDao.getAllBookByUserId(uid);
    }

    @Override
    public void delBook(String bid) {
        hotelDao.delBook(bid);
    }

    @Override
    public void updatePic(Hotel hotel) {
        hotelDao.updatePic(hotel);
    }

    @Override
    public void deleteHotelCommentById(String hotelCommentId) {
        hotelDao.deleteHotelCommentById(hotelCommentId);
    }

    @Override
    public void addHotelReport(Report report) {
        hotelDao.addHotelReport(report);
    }

    @Override
    public boolean findReport(Users user, Hotel hotel) {
        return hotelDao.findReport(user, hotel);
    }

    @Override
    public boolean findReportComment(Users user, HotelComment comment) {
        return hotelDao.findReportComment(user, comment);
    }

    @Override
    public HotelComment getHotelCommentById(String cid) {
        return hotelDao.getHotelCommentById(cid);
    }

    @Override
    public boolean checkBook(String uid, String hid) {
        if(hotelDao.checkBook(uid, hid))
            return true;
        else
            return false;
    }
    
}
