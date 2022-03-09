package com.vedant.notesapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vedant.notesapp.Activity.DeleteNotesActivity;
import com.vedant.notesapp.MainActivity;
import com.vedant.notesapp.Model.Notes;
import com.vedant.notesapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesItems;

    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
        allNotesItems = new ArrayList<Notes>();
    }

    public void searchNotes(List<Notes> filteredName) {
        this.notes = filteredName;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {

        Notes note = notes.get(position);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriorities.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriorities.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriorities.setBackgroundResource(R.drawable.red_shape);
                break;
        }
        holder.title.setText(note.notesTitle);
        holder.subtitles.setText(note.notesSubtitle);
        holder.date.setText(note.notesDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity, DeleteNotesActivity.class);
                intent.putExtra("id", note.id);
                intent.putExtra("title", note.notesTitle);
                intent.putExtra("subtitle", note.notesSubtitle);
                intent.putExtra("priority", note.notesPriority);
                intent.putExtra("note", note.notes);

                mainActivity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitles, date;
        View notesPriorities;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitles);
            subtitles = itemView.findViewById(R.id.notesSubtitles);
            date = itemView.findViewById(R.id.notesDates);
            notesPriorities = itemView.findViewById(R.id.notesPriorities);
        }
    }
}
