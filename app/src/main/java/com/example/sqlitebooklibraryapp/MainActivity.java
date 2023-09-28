package com.example.sqlitebooklibraryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addNewBook;
    MyDBHelper myDBHelper;

    private ArrayList<BookModel> bookList;
    private BookAdapter bookAdapter;

    ImageView noDataImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        addNewBook = findViewById(R.id.add_button);
        noDataImg = findViewById(R.id.noDataImg);

        addNewBook.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddDetails.class);
            startActivity(intent);
        });


        myDBHelper = new MyDBHelper(MainActivity.this);
        bookList = new ArrayList<>();
        storeDataInArrays();
        bookAdapter = new BookAdapter(MainActivity.this, this, bookList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(bookAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }


    void storeDataInArrays() {
        Cursor cursor = myDBHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            noDataImg.setVisibility(View.VISIBLE);
            // Create a scale animation to increase the size
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0f, 1.2f, // Start and end scale X
                    0f, 1.2f, // Start and end scale Y
                    Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point X relative to view width
                    Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point Y relative to view height
            scaleAnimation.setDuration(1000); // Animation duration in milliseconds
            scaleAnimation.setFillAfter(true); // Maintain the final state of the animation

            noDataImg.startAnimation(scaleAnimation);

        } else {
            while (cursor.moveToNext()) {
                BookModel bookModel = new BookModel();
                bookModel.setId(Integer.parseInt(cursor.getString(0)));
                bookModel.setBook_title(cursor.getString(1));
                bookModel.setBook_author(cursor.getString(2));
                bookModel.setBook_pages(cursor.getString(3));
                bookList.add(bookModel);
            }
            noDataImg.setVisibility(View.GONE);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.allDelete) {
            confirmDialog();
            Toast.makeText(this, "Delete All Data ", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you sure you want to delete all Data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDBHelper myDB = new MyDBHelper(MainActivity.this);
                myDB.deleteAllData();
                //Refresh activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
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