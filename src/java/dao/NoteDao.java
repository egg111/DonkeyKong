/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import pojo.Note;
import pojo.NoteComment;
import pojo.Report;
import pojo.Users;

/**
 *
 * @author hasee
 */
public interface NoteDao {
    void insertNote(Note note);
    void deleteNote(String nid);
    void updateNote(Note note);
    Note getNoteById(String nid);
    NoteComment getCommentById(String cid);
    ArrayList<Note> getAllNotes();
    ArrayList<Note> sortLikeNotes();
    ArrayList<Note> sortReleaseNotes();
    ArrayList<Note> getTopNotes();

    public void insertNoteComment(NoteComment noteComment);
    public ArrayList<NoteComment> getAllComments(Note note);

    public ArrayList<Note> getAllNotesByUserId(String user);

    public void addNoteReport(Report report);

    public boolean findReport(Users user, Note note);
    public boolean findReportComment(Users user, NoteComment comment);

    public void deleteNoteCommentById(String noteCommentId);
}
