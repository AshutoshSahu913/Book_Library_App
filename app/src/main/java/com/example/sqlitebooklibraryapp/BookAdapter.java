package com.example.sqlitebooklibraryapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.viewHolder> {
    Context context;
    private ArrayList<BookModel> bookList;
    Activity activity;

    Animation translate_anim;

    public BookAdapter(Activity activity, Context context, ArrayList<BookModel> bookList) {
        this.activity=activity;
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.viewHolder holder, int position) {
        BookModel book = bookList.get(position);
        holder.id.setText(String.valueOf(book.getId()));
        holder.title.setText(book.getBook_title());
        holder.author.setText(book.getBook_author());
        holder.pages.setText(String.valueOf(book.getBook_pages()));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateDetails.class);
                intent.putExtra("id", String.valueOf(book.getId()));
                intent.putExtra("title", String.valueOf(book.getBook_title()));
                intent.putExtra("author", String.valueOf(book.getBook_author()));
                intent.putExtra("pages", String.valueOf(book.getBook_pages()));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder {
        public TextView id, title, author, pages;
        LinearLayout layout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.bookId);
            title = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.bookAuthor);
            pages = itemView.findViewById(R.id.bookPages);
            layout = itemView.findViewById(R.id.linearLayoutList);
            //Animate RecyclerView
            translate_anim= AnimationUtils.loadAnimation(context,R.anim.translate);
            layout.setAnimation(translate_anim);
        }
    }

}
