package com.vedant.notesapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vedant.notesapp.Model.Notes;
import com.vedant.notesapp.Repository.NotesRepo;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepo repo;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highToLow;
    public LiveData<List<Notes>> lowToHigh;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        repo = new NotesRepo(application);
        getAllNotes = repo.getAllNotes;
        highToLow = repo.hightolow;
        lowToHigh = repo.lowtohigh;
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
