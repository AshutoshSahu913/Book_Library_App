package com.example.sqlitebooklibraryapp;

public class BookModel {
    String book_title,book_author;
    String  book_pages;
    int id;
    // Constructors
    public BookModel() {
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String  getBook_pages() {
        return book_pages;
    }

    public void setBook_pages(String book_pages) {
        this.book_pages = book_pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookModel(String book_title, String book_author, String book_pages, int id) {
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
        this.id = id;
    }
}
