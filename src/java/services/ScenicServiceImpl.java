/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ScenicDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pojo.Report;
import pojo.Scenic;
import pojo.ScenicComment;
import pojo.Users;

/**
 *
 * @author hasee
 */
@Service
public class ScenicServiceImpl implements ScenicService{
    
    @Autowired
    ScenicDao dao; 
    
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
    public Scenic getScenic(String sid) {
        Scenic sc = dao.getScenic(sid);
        if(sc !=null && sc.getScenicId().equals(sid)){
            return sc;
        }
        return null;
    }

    @Override
    public ArrayList<Scenic> getAllScenic() {
        return dao.getAllScenic();
    }

    @Override
    public void addScenic(Scenic scenic) {
        dao.insert(scenic);
    }

    @Override
    public void deleteScenic(String sid) {
        dao.delete(sid);
    }

    @Override
    public void update(Scenic scenic) {
        dao.update(scenic);
    }

    @Override
    public ArrayList<Scenic> getTopSenic() {
        return dao.getTopScenic();
    }

    @Override
    public void addScenicComment(ScenicComment scenicComment) {
        dao.insertScenicComment(scenicComment);
    }

    @Override
    public ArrayList<ScenicComment> getAllComments(Scenic scenic) {
        return dao.getAllComments(scenic);
    }

    @Override
    public void updatePic(Scenic scenic) {
        dao.updatePic(scenic);
    }

    @Override
    public void deleteScenicCommentById(String scenicCommentId) {
        dao.deleteScenicCommentById(scenicCommentId);
    }

    @Override
    public ScenicComment getScenicCommentById(String cid) {
        return dao.getScenicCommentById(cid);
    }

    @Override
    public boolean findReportComment(Users user, ScenicComment comment) {
        return dao.findReportComment(user, comment);
    }

    @Override
    public void addNoteReport(Report report) {
        dao.addNoteReport(report);
    }

    @Override
    public ArrayList<Scenic> getScenicByQD() {
        return dao.getScenicByQD();
    }
    
}