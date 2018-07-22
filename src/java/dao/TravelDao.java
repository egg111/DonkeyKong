/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import pojo.Tour;
import pojo.TourApply;
import pojo.TourJoin;
import pojo.Users;

/**
 *
 * @author hasee
 */
public interface TravelDao {

    void insertTravel(Tour tour);

    void deleteTravel(String tid);

    void updateTravel(Tour tour);

    Tour getTourById(String tid);

    ArrayList<Tour> getAllTravel();

    ArrayList<Tour> sortDaysTravel();

    ArrayList<Tour> sortCostTravel();

    ArrayList<Tour> getAllTravelByUserId(String uid);

    ArrayList<Tour> getAllTravelNotApproved();

    ArrayList<String> getApplyUsersId(String tid);

    void addTravelApply(TourApply tr);

    void approveTravel(String id);

    void delMember(String tid, String uid);

    ArrayList<TourJoin> getTravelById(String uid);
}