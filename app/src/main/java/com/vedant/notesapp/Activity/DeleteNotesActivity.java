package com.vedant.notesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vedant.notesapp.Model.Notes;
import com.vedant.notesapp.R;
import com.vedant.notesapp.ViewModel.NotesViewModel;
import com.vedant.notesapp.databinding.ActivityDeleteNotesBinding;

import java.util.Date;

public class DeleteNotesActivity extends AppCompatActivity {

    ActivityDeleteNotesBinding binding;
    String priority = "1";
    String stitle, ssubtitle, snotes, spriority;
    int sid;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        sid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("note");
        spriority = getIntent().getStringExtra("priority");

        binding.updateTitle.setText(stitle);
        binding.updateSubtitle.setText(ssubtitle);
        binding.updateNotes.setText(snotes);

        switch (spriority) {
            case "1":
                binding.greenPriority.setImageResource(R.drawable.layer);
                break;
            case "2":
                binding.yellowPriority.setImageResource(R.drawable.layer);
                break;
            case "3":
                binding.redPriority.setImageResource(R.drawable.layer);
                break;
        }


        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.layer);
            binding.redPriority.setImageResource(R.drawable.red_shape);
            binding.yellowPriority.setImageResource(R.drawable.yellow_shape);

            priority = "1";
        });
        binding.redPriority.setOnClickListener(v -> {
            binding.redPriority.setImageResource(R.drawable.layer);
            binding.greenPriority.setImageResource(R.drawable.green_shape);
            binding.yellowPriority.setImageResource(R.drawable.yellow_shape);
            priority = "3";

        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.yellowPriority.setImageResource(R.drawable.layer);
            binding.redPriority.setImageResource(R.drawable.red_shape);
            binding.greenPriority.setImageResource(R.drawable.green_shape);
            priority = "2";

        });

        binding.updateDoneBtn.setOnClickListener(v -> {
            String title = binding.updateTitle.getText().toString();
            String subtitle = binding.updateSubtitle.getText().toString();
            String notes = binding.updateNotes.getText().toString();

            UpdateNotes(title, subtitle, notes);
        });
    }

    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        Notes notes12 = new Notes();
        notes12.id = sid;
        notes12.notesTitle = title;
        notes12.notesSubtitle = subtitle;
        notes12.notes = notes;
        notes12.notesPriority = spriority;
        notes12.notesDate = sequence.toString();

        notesViewModel.updateNote(notes12);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            BottomSheetDialog dialog = new BottomSheetDialog(DeleteNotesActivity.this,R.style.AppBottomSheetDialogTheme);

            View view = LayoutInflater.from(this).inflate(R.layout.delete_bottom_sheet,
                    (LinearLayout) findViewById(R.id.bottomsheet));
            dialog.setContentView(view);
            Button no_delete, yes_delete;
            yes_delete = view.findViewById(R.id.yes_delete);
            no_delete = view.findViewById(R.id.no_delete);
            yes_delete.setOnClickListener(v -> {
                notesViewModel.deleteNote(sid);
                finish();
            });
            no_delete.setOnClickListener(v -> {
                dialog.dismiss();
            });
            dialog.show();
        }
        return true;
    }
}
