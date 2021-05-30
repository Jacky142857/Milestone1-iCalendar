package com.example.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * The AddEditNoteActivity class encapsulates the add note and edit note user interfaces.
 * */

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.calendar.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.calendar.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.calendar.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.calendar.EXTRA_PRIORITY";
    public static final String EXTRA_DDL =
            "com.example.calendar.EXTRA_DDL";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    private DatePicker datePickerDDL;
    private Calendar calendar = Calendar.getInstance();
    private int ddlYear;
    private int ddlMonth;
    private int ddlDay;

    private static String[] monthRep = new String[] {"January","February","March","April",
            "May","June","July","August","September","October","November","December"};

    /**
     * Initialise a add/edit note user interface, based on whether there is existing data
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker_priority);
        datePickerDDL = findViewById(R.id.date_picker_ddl);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ddlYear = year;
                ddlMonth = monthOfYear;
                ddlDay = dayOfMonth;
            }
        };

        datePickerDDL.init(Calendar.YEAR,Calendar.MONTH,Calendar.DATE,onDateChangedListener);
        datePickerDDL.setMinDate(calendar.getTimeInMillis());

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        //Check whether there is existing data to decide app bar title.
        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
            datePickerDDL.updateDate(ddlYear,ddlMonth,ddlDay);
        } else {
            setTitle("Add Note");
            datePickerDDL.updateDate(Calendar.YEAR,Calendar.MONTH,Calendar.DATE);
        }
    }

    /**
     * Save the user input to a new or existing note.
     * */

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int priority = numberPickerPriority.getValue();
        String ddl = monthRep[datePickerDDL.getMonth()] + " " + datePickerDDL.getDayOfMonth() + "," +
                datePickerDDL.getYear();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and a description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);
        data.putExtra(EXTRA_DDL, ddl);

        //check whether the note is newly created.
        int id = getIntent().getIntExtra(EXTRA_ID, -1);//-1 for invalid id
        if(id != -1) {
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();
    }

    /**
     * Instantiate the menu from the corresponding xml file.
     * */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    /**
     * Set up the link between the save button and saveNote() method.
     * */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save_note) {
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}