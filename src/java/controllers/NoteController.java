/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Note;
import pojo.NoteComment;
import pojo.Report;
import pojo.Users;
import services.NoteService;
import services.UsersService;
import util.DataUtils;
import util.DateUtils;
import util.IDUtils;

/**
 *
 * @author hasee
 */
@Controller
@RequestMapping(value = "/note")
public class NoteController {
    
    @Autowired
    NoteService ns;
    @Autowired
    UsersService us;
    @RequestMapping(value = "add" ,method = RequestMethod.POST)
    public String addNote(String noteTitle, String noteContent, String source, Model model, HttpSession session) throws UnsupportedEncodingException{
        String p = this.getClass().getResource("").getPath();
        String path = p.substring(0,p.indexOf("build"));
        Note note = new Note(IDUtils.getUUID(), (Users) session.getAttribute("CURRENT_USER"), DataUtils.translate(noteTitle), 0, DataUtils.translate(noteContent), DateUtils.getTimestamp(), DateUtils.getTimestamp());
        if(!source.equals("")){
            note.setNoteImg(DataUtils.saveImg(source, path+"web/resource/imags/note/"));
        }
        ns.addNote(note);
        Users user = (Users) session.getAttribute("CURRENT_USER");
        model.addAttribute("noteList", ns.getAllNotesByUserId(user.getUserId()));
        return "user/myNote";
    }
    
    @RequestMapping(value = "/delete" , method = RequestMethod.POST)
    @ResponseBody
    public String deleteNote(String nid){
        ns.deleteNote(nid);
        return "myNote";
    }
    
    @RequestMapping(value ="/edit" ,method = RequestMethod.POST)
    public String editNote(Note note, Model model, HttpSession session){
        ns.updateNote(note);
        Users user = (Users) session.getAttribute("CURRENT_USER");
        model.addAttribute("noteList", ns.getAllNotesByUserId(user.getUserId()));
        return "user/myNote";
    }
    
    @RequestMapping(value = "showAll",method = RequestMethod.GET)
    public String showAllNotes(Model model){
        model.addAttribute("noteList",ns.getAllNotes());
        return "user/myNote";
    }
    
    @RequestMapping(value = "getANote",method = RequestMethod.GET)
    public String getANote(String nid,Model model){
        Note note = ns.getNoteById(nid);
        note.setUsers(us.getAUser(note.getUsers().getUserId()));
        model.addAttribute("n", note);
        ArrayList<NoteComment> list = ns.getAllComments(note);
        for(NoteComment comments : list){
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "noteDetail";
    }
    
    @RequestMapping(value = "/commentedit", method = RequestMethod.POST)
    public String editNoteComment(String comment, String noteId, Model model, HttpSession session){
        Note note = ns.getNoteById(noteId);
        Users user = (Users) session.getAttribute("CURRENT_USER");
        NoteComment noteComment = new NoteComment(IDUtils.getUUID(), note, user, comment, DateUtils.getTimestamp());
        ns.addNoteComment(noteComment);
        ArrayList<NoteComment> list = ns.getAllComments(note);
        for(NoteComment comments : list){
            comments.setUsers(us.getAUser(comments.getUsers().getUserId()));
        }
        model.addAttribute("commentList", list);
        return "noteDetail";
    }
    
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public @ResponseBody void noteLike(String like, String noteId, Model model){
        int liked  = Integer.parseInt(like);
        Note note = ns.getNoteById(noteId);
        note.setNoteLike(++liked);
        ns.updateNote(note);
        model.addAttribute("n", note);
    }
    
    @RequestMapping(value = "/noteReport", method = RequestMethod.POST)
    public @ResponseBody String noteReport(String content, String noteId, Model model, HttpSession session){
        Note note = ns.getNoteById(noteId);
        Report report = new Report();
        report.setReportId(IDUtils.getUUID());
        report.setUser((Users) session.getAttribute("CURRENT_USER"));
        report.setNote(note);
        report.setReportContent(content);
        report.setReportTime(DateUtils.getTimestamp());
        if(ns.findReport(report.getUser(), report.getNote())){
            model.addAttribute("reportResult", "Your have already reported.");
            return "noteDetail";
        }
        ns.addNoteReport(report);
        model.addAttribute("reportResult", "Report successful.");
        return "noteDetail";
    }
    
    @RequestMapping(value = "/commentReport", method = RequestMethod.POST)
    public @ResponseBody String commentReport(String content, String commentId, Model model, HttpSession session){
        NoteComment comment = ns.getCommentById(commentId);
        Report report = new Report();
        report.setReportId(IDUtils.getUUID());
        report.setUser((Users) session.getAttribute("CURRENT_USER"));
        report.setNoteComment(comment);
        report.setReportContent(content);
        report.setReportTime(DateUtils.getTimestamp());
        System.out.println(comment.getNoteCommentId()+"+++++++>"+report.getNoteComment());
        if(ns.findReportComment(report.getUser(), report.getNoteComment())){
            System.out.println("exist.............");
            model.addAttribute("reportResult", "Your have already reported.");
            return "noteDetail";
        }
        ns.addNoteReport(report);
        System.out.println("nnnnnnnnnnnnnnnnnnnnnn");
        model.addAttribute("reportResult", "Report successful.");
        return "noteDetail";
    }
    
}
