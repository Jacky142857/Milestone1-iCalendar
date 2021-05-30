package com.example.calendar;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * The NoteDatabase class is a singleton that encapsulates a common SQLite database
 * for data storage.
 * */

@Database(entities = {Note.class}, version = 3)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;
    public abstract NoteDao noteDao();

    /**
     * Provide(or create and provide) a database for use.
     * @return A SQLite database
     * */
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    /**
     * Create a Callback for different implementation of the abstract method noteDao().
     * */
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        //Initialise the database with some example notes.
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1, "May 26,2021","May 26,2021"));
            noteDao.insert(new Note("Title 2", "Description 2", 2,"May 25,2021","May 26,2021"));
            noteDao.insert(new Note("Title 3", "Description 3", 3, "May 20,2021","May 26,2021"));
            return null;
        }
    }
}
