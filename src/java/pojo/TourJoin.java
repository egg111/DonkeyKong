package pojo;
// Generated 2018-6-10 21:52:05 by Hibernate Tools 4.3.1



/**
 * TourJoin generated by hbm2java
 */
public class TourJoin  implements java.io.Serializable {


     private String tjId;
     private Tour tour;
     private Users users;

    public TourJoin() {
    }

    public TourJoin(String tjId, Tour tour, Users users) {
       this.tjId = tjId;
       this.tour = tour;
       this.users = users;
    }
   
    public String getTjId() {
        return this.tjId;
    }
    
    public void setTjId(String tjId) {
        this.tjId = tjId;
    }
    public Tour getTour() {
        return this.tour;
    }
    
    public void setTour(Tour tour) {
        this.tour = tour;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }




}


