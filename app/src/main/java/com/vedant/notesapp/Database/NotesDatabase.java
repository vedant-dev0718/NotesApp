package com.vedant.notesapp.Database;

import android.content.Context;

import com.vedant.notesapp.Dao.Dao;
import com.vedant.notesapp.Model.Notes;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class},version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract Dao notesDao();
    public static NotesDatabase INSTANCE;
    
    public static NotesDatabase getDatabaseInstance(Context context) {
        if(INSTANCE==null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class
             ,"Notes_Database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
