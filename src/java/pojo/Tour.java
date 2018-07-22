package pojo;
// Generated 2018-6-8 16:29:36 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tour generated by hbm2java
 */
public class Tour  implements java.io.Serializable {


     private String tourId;
     private String tourTitle;
     private String scenicName;
     private double tourPrice;
     private int limitNum;
     private String tripMode;
     private String hotelName;
     private int isApprove;
     private Date tourBeginTime;
     private Date tourEndTime;
     private Date applyEndTime;
     private Set tourApplies = new HashSet(0);
     private Set tourComments = new HashSet(0);

    public Tour() {
    }

	
    public Tour(String tourId, String tourTitle, String scenicName, double tourPrice, int limitNum, String tripMode, String hotelName, int isApprove, Date tourBeginTime, Date tourEndTime, Date applyEndTime) {
        this.tourId = tourId;
        this.tourTitle = tourTitle;
        this.scenicName = scenicName;
        this.tourPrice = tourPrice;
        this.limitNum = limitNum;
        this.tripMode = tripMode;
        this.hotelName = hotelName;
        this.isApprove = isApprove;
        this.tourBeginTime = tourBeginTime;
        this.tourEndTime = tourEndTime;
        this.applyEndTime = applyEndTime;
    }
    public Tour(String tourId, String tourTitle, String scenicName, double tourPrice, int limitNum, String tripMode, String hotelName, int isApprove, Date tourBeginTime, Date tourEndTime, Date applyEndTime, Set tourApplies, Set tourComments) {
       this.tourId = tourId;
       this.tourTitle = tourTitle;
       this.scenicName = scenicName;
       this.tourPrice = tourPrice;
       this.limitNum = limitNum;
       this.tripMode = tripMode;
       this.hotelName = hotelName;
       this.isApprove = isApprove;
       this.tourBeginTime = tourBeginTime;
       this.tourEndTime = tourEndTime;
       this.applyEndTime = applyEndTime;
       this.tourApplies = tourApplies;
       this.tourComments = tourComments;
    }
   
    public String getTourId() {
        return this.tourId;
    }
    
    public void setTourId(String tourId) {
        this.tourId = tourId;
    }
    public String getTourTitle() {
        return this.tourTitle;
    }
    
    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }
    public String getScenicName() {
        return this.scenicName;
    }
    
    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }
    public double getTourPrice() {
        return this.tourPrice;
    }
    
    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }
    public int getLimitNum() {
        return this.limitNum;
    }
    
    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }
    public String getTripMode() {
        return this.tripMode;
    }
    
    public void setTripMode(String tripMode) {
        this.tripMode = tripMode;
    }
    public String getHotelName() {
        return this.hotelName;
    }
    
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public int getIsApprove() {
        return this.isApprove;
    }
    
    public void setIsApprove(int isApprove) {
        this.isApprove = isApprove;
    }
    public Date getTourBeginTime() {
        return this.tourBeginTime;
    }
    
    public void setTourBeginTime(Date tourBeginTime) {
        this.tourBeginTime = tourBeginTime;
    }
    public Date getTourEndTime() {
        return this.tourEndTime;
    }
    
    public void setTourEndTime(Date tourEndTime) {
        this.tourEndTime = tourEndTime;
    }
    public Date getApplyEndTime() {
        return this.applyEndTime;
    }
    
    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }
    public Set getTourApplies() {
        return this.tourApplies;
    }
    
    public void setTourApplies(Set tourApplies) {
        this.tourApplies = tourApplies;
    }
    public Set getTourComments() {
        return this.tourComments;
    }
    
    public void setTourComments(Set tourComments) {
        this.tourComments = tourComments;
    }




}


