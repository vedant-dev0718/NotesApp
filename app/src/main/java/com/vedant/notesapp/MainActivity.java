package com.vedant.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vedant.notesapp.Activity.InsertNotesActivity;
import com.vedant.notesapp.Adapters.NotesAdapter;
import com.vedant.notesapp.Model.Notes;
import com.vedant.notesapp.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {
    NotesViewModel notesViewModel;
    FloatingActionButton mFloatingActionButton;
    RecyclerView mRecyclerView;
    TextView noFilter, HightoLow, LowToHigh;
    List<Notes> filternotesalllist;
    NotesAdapter  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.notesRecycler);

        noFilter = findViewById(R.id.noFilter);
        HightoLow = findViewById(R.id.HightoLow);
        LowToHigh = findViewById(R.id.LowtoHigh);

        noFilter.setBackgroundResource(R.drawable.selected_filter);
        mFloatingActionButton = findViewById(R.id.notesFloatBtn);

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
            }
        });

        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filternotesalllist = notes;
            }
        });


        noFilter.setOnClickListener(v -> {
            loadData(1);
            noFilter.setBackgroundResource(R.drawable.selected_filter);
            HightoLow.setBackgroundResource(R.drawable.filter_shape);
            LowToHigh.setBackgroundResource(R.drawable.filter_shape);


        });
        HightoLow.setOnClickListener(v -> {
            loadData(2);

            HightoLow.setBackgroundResource(R.drawable.selected_filter);
            LowToHigh.setBackgroundResource(R.drawable.filter_shape);
            noFilter.setBackgroundResource(R.drawable.filter_shape);

        });
        LowToHigh.setOnClickListener(v -> {
            loadData(3);

            LowToHigh.setBackgroundResource(R.drawable.selected_filter);
            HightoLow.setBackgroundResource(R.drawable.filter_shape);
            noFilter.setBackgroundResource(R.drawable.filter_shape);

        });


    }

    private void loadData(int i) {
        if (i == 1) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });


        } else if (i == 2) {
            notesViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        } else if (i == 3) {
            notesViewModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist = notes;
                }
            });
        }
    }

    public void setAdapter(List<Notes> notes) {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
         adapter = new NotesAdapter(MainActivity.this, notes);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("search notes here.....");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });

        return true;

    }

    private void NotesFilter(String newText) {
        ArrayList<Notes> filternotes = new ArrayList<>();
        for (Notes notes : this.filternotesalllist) {
            if (notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText)) {
                filternotes.add(notes);
            }
        }

        this.adapter.searchNotes(filternotes);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return true;
    }
}
