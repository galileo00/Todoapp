package com.example.todoapp.DataBase.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    int id ;
    @ColumnInfo
    String name ;
    @ColumnInfo
    String date ;
    @ColumnInfo
    String note ;
    @ColumnInfo
    String time ;

    public Todo() {

    }

@Ignore
    public Todo(String name, String note) {
        this.name = name;
        this.note = note;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time = new SimpleDateFormat("a hh:mm");
        this.date = date.format(calendar.getTime());
        this.time = time.format(calendar.getTime());
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
