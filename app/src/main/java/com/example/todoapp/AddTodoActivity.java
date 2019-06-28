package com.example.todoapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.todoapp.Base.BaseActivity;
import com.example.todoapp.DataBase.Models.Todo;
import com.example.todoapp.DataBase.MyDataBase;

public class AddTodoActivity extends BaseActivity {


    private TextInputLayout titleEditText;
    private TextInputLayout contentEditText;
    private Button addButton;
    private TextInputEditText titleText;
    private EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);


        titleEditText = findViewById(R.id.titleLayout);
        contentEditText = findViewById(R.id.contentLayout);

        titleText = findViewById(R.id.titleText);
        contentText = findViewById(R.id.contentText);


        addButton = findViewById(R.id.addButton);


        addButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String title = titleText.getText().toString();
                String content = contentText.getText().toString();
                if (title.equals("")) {
                    titleEditText.setError("Please add the title");

                } else titleEditText.setError(null);


                if (content.equals(""))
                    contentEditText.setError("please add the details ");
                else
                    contentEditText.setError(null);

                if ( !title.equals("")&& !content.equals("")){

                    MyDataBase.getInstance(activity)
                            .todoDao()
                            .addTodo(new Todo(title, content));
                    showConfirmationMessage("Done", "Todo added successfully", "OK", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            finish();

                        }


            });


        }
    }





});
    }}