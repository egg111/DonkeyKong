/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import pojo.Report;
import pojo.Scenic;
import pojo.ScenicComment;
import pojo.Users;

/**
 *
 * @author hasee
 */
public interface ScenicService {
    Scenic getScenic(String sid);
    ScenicComment getScenicCommentById(String cid);
    ArrayList<Scenic> getAllScenic();
    ArrayList<Scenic> getTopSenic();
    ArrayList<Scenic> getScenicByQD();
    void addScenic(Scenic scenic);
    void deleteScenic(String sid);
    void update(Scenic scenic);

    public void addScenicComment(ScenicComment scenicComment);

    public ArrayList<ScenicComment> getAllComments(Scenic scenic);

    public void updatePic(Scenic scenic);

    public void deleteScenicCommentById(String scenicCommentId);
    
    public void addNoteReport(Report report);
    
    public boolean findReportComment(Users user, ScenicComment comment);
    
}
