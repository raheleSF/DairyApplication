package com.navin.dairyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.navin.dairyapplication.database.DiaryDbadapter;
import com.navin.dairyapplication.models.Task;

import java.util.Calendar;

public class TaskActivity extends AppCompatActivity {

    AppCompatButton btn_back;
    AppCompatButton btn_time;
    AppCompatButton btn_date;
    AppCompatButton btn_save;
    AppCompatEditText edt_title;
    AppCompatEditText edt_desc;
    private Calendar calendar;
    private AppCompatTextView txt_date;
    private AppCompatTextView txt_time;
    private int year, month, day;
    private int hour, minute;
    String timeSelected = "";
    String dateSelected = "";

    DiaryDbadapter diaryDbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        diaryDbadapter = new DiaryDbadapter(getApplicationContext());

        btn_back = findViewById(R.id.btn_back);
        btn_time = findViewById(R.id.btn_time);
        btn_date = findViewById(R.id.btn_setdate);
        txt_date = findViewById(R.id.date_selected);
        btn_save = findViewById(R.id.btn_save);
        edt_title = findViewById(R.id.edt_title);
        edt_desc = findViewById(R.id.edt_disc);
        txt_time = findViewById(R.id.txt_timer);


        calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        //showTime(hour , minute);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(999);
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });


        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(TaskActivity.this
                        , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        showTime(i, i1);


                    }
                }, 00, 00, true);
//                time
                timePickerDialog.show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_title.getText().toString().length() > 0
                        && edt_desc.getText().toString().length() > 0
                        && dateSelected.length() > 0 && timeSelected.length() > 0) {

                    Task task = new Task();
                    task.setTitle(edt_title.getText().toString());
                    task.setDesc(edt_desc.getText().toString());
                    task.setDate(dateSelected);
                    task.setTime(timeSelected);

                    long result = diaryDbadapter.insertTask(task);
                    if (result > 0) {
                        Snackbar.make(view, getString(R.string.success_insert), Snackbar.LENGTH_LONG)
                                .show();
                        Log.e("message", "success");
                    } else {
                        Snackbar.make(view, getString(R.string.error_insert), Snackbar.LENGTH_LONG)
                                .show();
                        Log.e("message", "error");
                    }
                } else {
                    Snackbar.make(view, "please enter inputs", Snackbar.LENGTH_LONG)
                            .show();
                }


            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        txt_date.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
        dateSelected = txt_date.getText().toString();
    }

    private void showTime(int hour, int minute) {
        txt_time.setText(new StringBuilder().append(hour).append(":")
                .append(minute));
        timeSelected = txt_time.getText().toString();
    }


}
