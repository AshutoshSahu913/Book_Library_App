package com.example.sqlitebooklibraryapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddDetails extends AppCompatActivity {

    EditText title, author, pages;
    Button addDetails;

    @SuppressLint({"MissingInflatedId", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        title = findViewById(R.id.addTitle);
        author = findViewById(R.id.addAuthorName);
        pages = findViewById(R.id.addPages);
        addDetails = findViewById(R.id.addDetails);

        addDetails.setOnClickListener(v -> {
            MyDBHelper myDBHelper = new MyDBHelper(AddDetails.this);
            if (title.getText().toString().isEmpty() && !author.getText().toString().isEmpty() && !pages.getText().toString().isEmpty()) {
                Toast.makeText(AddDetails.this, "Empty Details Title", Toast.LENGTH_SHORT).show();
                title.setBackground(getResources().getDrawable(R.drawable.rectangle_red));
            }
            if (author.getText().toString().isEmpty()) {
                Toast.makeText(AddDetails.this, "Empty Details Title", Toast.LENGTH_SHORT).show();
                author.setBackground(getResources().getDrawable(R.drawable.rectangle_red));
            }
            if (pages.getText().toString().isEmpty()) {
                Toast.makeText(AddDetails.this, "Empty Details Title", Toast.LENGTH_SHORT).show();
                author.setBackground(getResources().getDrawable(R.drawable.rectangle_red));
            }

            if (title.getText().toString().isEmpty() && author.getText().toString().isEmpty() && pages.getText().toString().isEmpty()) {
                Toast.makeText(AddDetails.this, "Empty Details", Toast.LENGTH_SHORT).show();
                title.setBackground(getResources().getDrawable(R.drawable.rectangle_red));
                author.setBackground(getResources().getDrawable(R.drawable.rectangle_red));
                pages.setBackground(getResources().getDrawable(R.drawable.rectangle_red));
            } else {
                title.setBackground(getResources().getDrawable(R.drawable.rectangle));
                String titleText = title.getText().toString().trim();
                String authorText = author.getText().toString().trim();
                String pagesText = pages.getText().toString().trim();

// for title
                if (TextUtils.isEmpty(titleText) || titleText.trim().isEmpty()) {
                    // Empty or whitespace-only title
                    Toast.makeText(this, "Please enter a valid title", Toast.LENGTH_SHORT).show();
                    return;
                }

// for author
                if (TextUtils.isEmpty(authorText) || authorText.trim().isEmpty()) {
                    // Empty or whitespace-only author
                    Toast.makeText(this, "Please enter a valid author", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }
// for pages
                if (TextUtils.isEmpty(pagesText) || pagesText.trim().isEmpty()) {
                    // Empty or whitespace-only pages
                    Toast.makeText(this, "Please enter a valid number of pages", Toast.LENGTH_SHORT).show();
                    return; // Stop further execution
                }

                try {
                    int pageCount = Integer.parseInt(pagesText);
                    // Valid input, add to the database
                    title.setBackground(getResources().getDrawable(R.drawable.rectangle));
                    author.setBackground(getResources().getDrawable(R.drawable.rectangle));
                    pages.setBackground(getResources().getDrawable(R.drawable.rectangle));

                    myDBHelper.addBook(titleText, authorText, pageCount);
                } catch (NumberFormatException e) {
                    // Invalid input (non-integer pages), show a toast message
                    Toast.makeText(this, "Please enter a valid integer for pages", Toast.LENGTH_SHORT).show();
                }

/*
                myDBHelper.addBook(title.getText().toString().trim(),
                        author.getText().toString().trim(),
                        Integer.parseInt(pages.getText().toString().trim()));*/
            }
        });
    }
}