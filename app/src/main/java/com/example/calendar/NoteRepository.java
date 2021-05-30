package com.example.calendar;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * The NoteRepository class encapsulate a repository for the execution of data processing.
 * */

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    /**
     * Starting an insert operation by creating and executing an insertion async task.
     * */

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    /**
     * Starting a update operation by creating and executing a update async task.
     * */

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    /**
     * Starting a delete operation by creating and executing a delete async task.
     * */

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    /**
     * Starting a deleteAll operation by creating and executing a deleteAll async task.
     * */

    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    /**
     * A getter method to get all current notes in the database
     * @return the avaliable notes in the current database.
     * */
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    /**
     * The async representation of an insertion task.
     * */

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    /**
     * The async representation of a update task.
     * */
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    /**
     * The async representation of a delete task.
     * */

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    /**
     * The async representation of a deleteAll task.
     * */

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;
        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
