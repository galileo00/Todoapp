package com.example.todoapp.DataBase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.todoapp.DataBase.DAOs.TodoDao;
import com.example.todoapp.DataBase.Models.Todo;

@Database(entities = {Todo.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    public abstract TodoDao todoDao();

    private static MyDataBase myDataBase;

    public static MyDataBase getInstance(Context context) {

        if (myDataBase == null) {
            myDataBase = Room.databaseBuilder(context.getApplicationContext(),
                    MyDataBase.class, "Todo_Database")
                    .allowMainThreadQueries()
                    .build();

        }
        return myDataBase;
    }


}
