package com.example.notesapp;

public class NoteModel {
    private int noteID;
    private String noteTitle;
    private String noteDesc;
    private String noteDate;

    public NoteModel(int noteID, String noteTitle, String noteDesc, String noteDate) {
        this.noteID = noteID;
        this.noteTitle = noteTitle;
        this.noteDesc = noteDesc;
        this.noteDate = noteDate;
    }

    public NoteModel(int noteID, String noteTitle, String noteDesc) {
        this.noteID = noteID;
        this.noteTitle = noteTitle;
        this.noteDesc = noteDesc;
    }

    public NoteModel(String noteTitle, String noteDesc, String noteDate) {
        this.noteTitle = noteTitle;
        this.noteDesc = noteDesc;
        this.noteDate = noteDate;
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDesc() {
        return noteDesc;
    }

    public void setNoteDesc(String noteDesc) {
        this.noteDesc = noteDesc;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }
}
