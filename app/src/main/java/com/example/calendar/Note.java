package com.example.calendar;

import android.widget.TextView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The Note class encapsulate an entity with basic attributes.
 * */

@Entity(tableName = "note_table")
public class Note {

    /**
     * Attributes of a note stored in the database.
     * The ID of a note will be automatically generated.
     * */

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private int priority;
    private String currentDate;
    private String ddl;

    public Note(String title, String description, int priority, String currentDate, String ddl) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.currentDate = currentDate;
        this.ddl = ddl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getDdl() {
        return ddl;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getDate() {
        return currentDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
