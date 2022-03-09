package com.vedant.notesapp.Dao;

import com.vedant.notesapp.Model.Notes;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> hightolow();

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowtohigh();
    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
