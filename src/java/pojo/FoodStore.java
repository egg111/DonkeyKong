package pojo;
// Generated 2018-5-28 23:08:24 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * FoodStore generated by hbm2java
 */
public class FoodStore  implements java.io.Serializable {


     private String foodStoreId;
     private Areas areas;
     private String areaName;
     private String foodStoreName;
     private double avgPrice;
     private String storeAddress;
     private Integer liked;
     private String storeImg;
     private String storeContent;
     private Set<FoodStoreComment> foodStoreComments = new HashSet<FoodStoreComment>(0);

    public FoodStore() {
    }

	
    public FoodStore(String foodStoreId, Areas areas, String foodStoreName, double avgPrice, String storeAddress) {
        this.foodStoreId = foodStoreId;
        this.areas = areas;
        this.foodStoreName = foodStoreName;
        this.avgPrice = avgPrice;
        this.storeAddress = storeAddress;
    }

    public FoodStore(String foodStoreId, String areaName, String foodStoreName, String storeAddress, Integer liked, String storeImg, String storeContent) {
        this.foodStoreId = foodStoreId;
        this.areaName = areaName;
        this.foodStoreName = foodStoreName;
        this.storeAddress = storeAddress;
        this.liked = liked;
        this.storeImg = storeImg;
        this.storeContent = storeContent;
    }
    
    public FoodStore(String foodStoreId, Areas areas, String foodStoreName, double avgPrice, String storeAddress, Integer liked, String storeImg, String storeContent, Set<FoodStoreComment> foodStoreComments) {
       this.foodStoreId = foodStoreId;
       this.areas = areas;
       this.foodStoreName = foodStoreName;
       this.avgPrice = avgPrice;
       this.storeAddress = storeAddress;
       this.liked = liked;
       this.storeImg = storeImg;
       this.storeContent = storeContent;
       this.foodStoreComments = foodStoreComments;
    }
   
    public String getFoodStoreId() {
        return this.foodStoreId;
    }
    
    public void setFoodStoreId(String foodStoreId) {
        this.foodStoreId = foodStoreId;
    }
    public Areas getAreas() {
        return this.areas;
    }
    
    public void setAreas(Areas areas) {
        this.areas = areas;
    }
    public String getFoodStoreName() {
        return this.foodStoreName;
    }
    
    public void setFoodStoreName(String foodStoreName) {
        this.foodStoreName = foodStoreName;
    }
    public double getAvgPrice() {
        return this.avgPrice;
    }
    
    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }
    public String getStoreAddress() {
        return this.storeAddress;
    }
    
    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
    public Integer getLiked() {
        return this.liked;
    }
    
    public void setLiked(Integer liked) {
        this.liked = liked;
    }
    public String getStoreImg() {
        return this.storeImg;
    }
    
    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }
    public String getStoreContent() {
        return this.storeContent;
    }
    
    public void setStoreContent(String storeContent) {
        this.storeContent = storeContent;
    }
    public Set<FoodStoreComment> getFoodStoreComments() {
        return this.foodStoreComments;
    }
    
    public void setFoodStoreComments(Set<FoodStoreComment> foodStoreComments) {
        this.foodStoreComments = foodStoreComments;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }




}


