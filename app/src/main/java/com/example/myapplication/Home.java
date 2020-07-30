package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.model.Book;
import com.example.myapplication.model.Genre;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    DatabaseHelper db;
    Adapter adapter;
    ArrayList<Book> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = new DatabaseHelper(this);

        Button btnAddGenre, btnAddBook;
        Spinner spinner;
        final ListView listView;

        btnAddGenre = findViewById(R.id.btnAddGenre);
        btnAddBook = findViewById(R.id.btnAddBook);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);

        ArrayList<Genre> listGenre = db.getGenre();
        final ArrayList<String> listName = new ArrayList<>();

        listName.add("TAT CA");

        for(int i=0; i<listGenre.size(); i++){
            listName.add(listGenre.get(i).getName());
        }

        ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listName);
        spinner.setAdapter(spinAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if(listName.get(i).equals("TAT CA")){
                        list = db.getBook();
                        adapter = new Adapter(Home.this, list);
                        listView.setAdapter(adapter);
                    }
                    else {
                        int id = db.getGenreId(listName.get(i));
                        list = db.getBookByGenre(id);
                        adapter = new Adapter(Home.this, list);
                        listView.setAdapter(adapter);
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAddGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Genre genre = new Genre("VAN HOC");
                Genre genre1 = new Genre("LANG MAN");
                db.addGenre(genre);
                db.addGenre(genre1);
                Toast.makeText(getBaseContext(), "THEM THANH CONG", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book1 = new Book("Sach1", "tg1", 1);
                Book book2 = new Book("Sach2", "tg2", 2);
                Book book3 = new Book("Sach3", "tg3", 1);
                Book book4 = new Book("Sach4", "tg4", 2);
                Book book5 = new Book("Sach5", "tg5", 1);

                db.addBook(book1);
                db.addBook(book2);
                db.addBook(book3);
                db.addBook(book4);
                db.addBook(book5);

                Toast.makeText(getBaseContext(), "THEM THANH CONG", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
