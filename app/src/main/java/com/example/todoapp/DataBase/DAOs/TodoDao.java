package com.example.todoapp.DataBase.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.todoapp.DataBase.Models.Todo;

import java.util.List;

@Dao
public interface TodoDao {


    @Insert
    void addTodo(Todo todo);

    @Update
    void updateTodo(Todo todo);

    @Delete
    void deleteTodo(Todo todo);

    @Query("Delete  From Todo;")
    void deleteAllTodos();

    @Query("select * from Todo;")
    List<Todo> selectAllTodos();
}
