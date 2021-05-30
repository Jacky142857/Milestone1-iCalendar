package com.example.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

/**
 * The NoteAdapter class provides adapters for Note to be used in the ListReminderActivity.
 * */

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    /**
     * Compare two note objects to see whether they refer to the same object and have the same content
     * */

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Note oldItem, @NonNull @NotNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Note oldItem, @NonNull @NotNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority() &&
                    oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getDdl().equals(newItem.getDdl());
        }
    };

    /**
     * Access the view of the note item in the xml file and instantiate a NoteHolder.
     * @return A NoteHolder with the view obtained from note_item.xml.
     * */

    @NotNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemView);
    }

    /**
     * To be more efficient, update the NoteHolder with new data when the number of notes
     * displayed on the screen reaches maximum and the user swipe downward to see more notes.
     * */

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteAdapter.NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.textViewDate.setText(currentNote.getDate());
        holder.textViewDDL.setText(currentNote.getDdl());
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    /**
     * The NoteHolder class is a customized implementation of ViewHolder, with relevant fields
     * present in the database(title, description and priority).
     * */

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private TextView textViewDate;
        private TextView textViewDDL;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewDDL = itemView.findViewById(R.id.text_view_ddl);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }

    }

    /**
     * An interface that modules the resultant activities for a click on a note item.
     * */

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
