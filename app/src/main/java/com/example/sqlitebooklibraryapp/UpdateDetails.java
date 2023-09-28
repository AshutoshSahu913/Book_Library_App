package com.example.sqlitebooklibraryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateDetails extends AppCompatActivity {

    EditText title_input, author_input, pages_input;

    Button updateBtn, deleteBtn;

    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        title_input = findViewById(R.id.updateTitle);
        author_input = findViewById(R.id.updateAuthorName);
        pages_input = findViewById(R.id.updatePages);
        updateBtn = findViewById(R.id.updateDetails);
        deleteBtn = findViewById(R.id.deleteDetails);


        //First we call this
        getAndSetIntentData();

        //set action bar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper mtDB = new MyDBHelper(UpdateDetails.this);
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();
                try {
                    int pageCount = Integer.parseInt(pages);
                    //And only the we call this
                    mtDB.updateData(id, title, author, pages);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid integer for pages", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    public void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //Getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //setting intent data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ? ");
        builder.setMessage("Are you sure you want to delete " + title + " ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDBHelper myDB = new MyDBHelper(UpdateDetails.this);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(UpdateDetails.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}