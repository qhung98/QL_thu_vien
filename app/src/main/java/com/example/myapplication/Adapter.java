package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.model.Book;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Book> {
    Context context;
    ArrayList<Book> list;
    DatabaseHelper db;

    public Adapter(@NonNull Context context, ArrayList<Book> list) {
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        TextView tvName, tvAuthor, tvGenre;
        tvName = view.findViewById(R.id.tvName);
        tvAuthor = view.findViewById(R.id.tvAuthor);
        tvGenre = view.findViewById(R.id.tvGenre);

        tvName.setText(list.get(position).getName());
        tvAuthor.setText(list.get(position).getAuthor());

        db = new DatabaseHelper(context);

        String genreName = db.getGenreName(list.get(position).getGenreId());
        tvGenre.setText(genreName);

        return view;
    }
}
