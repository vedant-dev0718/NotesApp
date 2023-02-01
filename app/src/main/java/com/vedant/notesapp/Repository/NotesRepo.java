package com.vedant.notesapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.vedant.notesapp.Dao.Dao;
import com.vedant.notesapp.Database.NotesDatabase;
import com.vedant.notesapp.Model.Notes;

import java.util.List;

public class NotesRepo {

    public Dao notesDao;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;


    public NotesRepo(Application context) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(context);
        notesDao = database.notesDao();
        getAllNotes = notesDao.getAllNotes();
        hightolow = notesDao.hightolow();
        lowtohigh = notesDao.lowtohigh();
    }

    public void insertUser(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteUser(int id) {
        notesDao.deleteNotes(id);
    }

    public void updateUser(Notes notes) {
        notesDao.updateNotes(notes);
    }

}
