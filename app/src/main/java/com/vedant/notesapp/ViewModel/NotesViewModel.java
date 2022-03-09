package com.vedant.notesapp.ViewModel;

import android.app.Application;

import com.vedant.notesapp.Model.Notes;
import com.vedant.notesapp.Repository.NotesRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepo repo;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repo = new NotesRepo(application);
        getAllNotes = repo.getAllNotes;
        hightolow = repo.hightolow;
        lowtohigh = repo.lowtohigh;
    }

    public void insertNote(Notes notes) {
        repo.insertUser(notes);
    }

    public void deleteNote(int id) {
        repo.deleteUser(id);
    }

    public void updateNote(Notes notes) {
        repo.updateUser(notes);
    }
}
