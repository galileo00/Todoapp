package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.todoapp.Adapters.TodoRecyclerAdapter;
import com.example.todoapp.Base.BaseActivity;
import com.example.todoapp.DataBase.Models.Todo;
import com.example.todoapp.DataBase.MyDataBase;

import java.util.List;

public class MainActivity extends BaseActivity {

    RecyclerView recyclerView;
    TodoRecyclerAdapter adapter;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TodoRecyclerAdapter(null);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemViewClickListener(new TodoRecyclerAdapter.onItemCLickListener() {
            @Override
            public void onItemClick(int pos, Todo todo) {
                showMessage(todo.getName(), todo.getNote(), "OK");
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTodoActivity.class));
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {

                showYesNoMessage("delete Todo", "are you sure to delete the todo ? ", "Yes", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        MyDataBase.getInstance(activity)
                                .todoDao()
                                .deleteTodo(adapter.getTodo(viewHolder.getAdapterPosition()));

                        updateList();
                        dialog.dismiss();


                    }
                }, "No", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updateList();
                        dialog.dismiss();

                    }
                });


            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateList();


    }

    private void updateList() {
        List<Todo> list = MyDataBase.getInstance(activity)
                .todoDao()
                .selectAllTodos();
        adapter.changeLIst(list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delet) {

            MyDataBase.getInstance(activity)
                    .todoDao()
                    .deleteAllTodos();
            updateList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
