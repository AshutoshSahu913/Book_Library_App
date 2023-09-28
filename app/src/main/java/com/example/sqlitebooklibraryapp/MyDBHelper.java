package com.example.sqlitebooklibraryapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDBHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "booksDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String BOOK_ID = "id";
    private static final String BOOK_TITLE = "book_title";
    private static final String BOOK_AUTHOR = "book_author";
     private static final String BOOK_PAGES = "books_pages";


    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + BOOK_TITLE + " TEXT," + BOOK_AUTHOR + " TEXT," + BOOK_PAGES + " INTEGER" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(String title, String author, int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOK_TITLE, title);
        cv.put(BOOK_AUTHOR, author);
        cv.put(BOOK_PAGES, pages);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }
    }


    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

   public void updateData(String row_id,String title,String author,String pages){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(BOOK_TITLE,title);
        cv.put(BOOK_AUTHOR,author);
        cv.put(BOOK_PAGES,pages);
        long result=db.update(TABLE_NAME,cv,"id=?",new String[]{row_id});
        if (result==-1){
            Toast.makeText(context, "Failed to Updated", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }
   }
   public void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result=db.delete(TABLE_NAME,"id=?",new String[]{row_id});
        if (result==-1){
            Toast.makeText(context,"Failed to delete",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Successfully Deleted",Toast.LENGTH_SHORT).show();
        }
   }
   public void deleteAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
   }
}