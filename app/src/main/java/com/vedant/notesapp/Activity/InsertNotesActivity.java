
package com.vedant.notesapp.Activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vedant.notesapp.Model.Notes;
import com.vedant.notesapp.R;
import com.vedant.notesapp.ViewModel.NotesViewModel;
import com.vedant.notesapp.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title, subtitle, notes;
    NotesViewModel notesViewModel;
    FloatingActionButton mFloatingActionButton;
    String priority = "1";
    ImageView yellowPriority, greenPriority, redPriority;
    EditText insertTitle, insertSubtitle, insertNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_notes);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());

        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        mFloatingActionButton = findViewById(R.id.doneNotesBtn);
        yellowPriority = findViewById(R.id.yellowPriority);
        redPriority = findViewById(R.id.redPriority);
        greenPriority = findViewById(R.id.greenPriority);
        insertTitle = findViewById(R.id.insert_title);
        insertSubtitle = findViewById(R.id.insert_subtitle);
        insertNotes = findViewById(R.id.insert_notes);


        mFloatingActionButton.setOnClickListener(v -> {

            title = insertTitle.getText().toString();
            subtitle = insertSubtitle.getText().toString();
            notes = insertNotes.getText().toString();

            CreateNotes(title, subtitle, notes);
        });

        greenPriority.setOnClickListener(v -> {

            greenPriority.setImageResource(R.drawable.layer);
            redPriority.setImageResource(R.drawable.red_shape);
            yellowPriority.setImageResource(R.drawable.yellow_shape);

            priority = "1";
        });
        redPriority.setOnClickListener(v -> {
            redPriority.setImageResource(R.drawable.layer);
            greenPriority.setImageResource(R.drawable.green_shape);
            yellowPriority.setImageResource(R.drawable.yellow_shape);
            priority = "3";

        });
        yellowPriority.setOnClickListener(v -> {
            yellowPriority.setImageResource(R.drawable.layer);
            redPriority.setImageResource(R.drawable.red_shape);
            greenPriority.setImageResource(R.drawable.green_shape);
            priority = "2";

        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());


        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.notesDate = sequence.toString();

        notesViewModel.insertNote(notes1);

        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
