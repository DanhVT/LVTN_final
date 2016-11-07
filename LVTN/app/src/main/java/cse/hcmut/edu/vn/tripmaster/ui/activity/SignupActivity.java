package cse.hcmut.edu.vn.tripmaster.ui.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import cse.hcmut.edu.vn.tripmaster.R;

public class SignupActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private EditText input_birth_day;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        input_birth_day = (EditText) findViewById(R.id.input_birth_day);



    }


}
