package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Book;
import com.example.myapplication.model.Genre;
import com.example.myapplication.model.User;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    private static final String DB_NAME = "QLTV";
    private static final String USER_TBL = "user";
    private static final String GENRE_TBL = "genre";
    private static final String BOOK_TBL = "book";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query1 = "CREATE TABLE " + USER_TBL +"(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)";
        String query2 = "CREATE TABLE " + GENRE_TBL +"(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        String query3 = "CREATE TABLE " + BOOK_TBL +"(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, author TEXT, genreId INTEGER)";

        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "  + USER_TBL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "  + GENRE_TBL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "  + BOOK_TBL);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());

        db.insert(USER_TBL, null, contentValues);
    }

    public boolean checkLogin(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TBL + " WHERE username=? AND password=?", new String[]{user.getUsername(), user.getPassword()});

        if(cursor.getCount()>0){
            return true;
        }

        return false;
    }

    public void addGenre(Genre genre){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", genre.getName());

        db.insert(GENRE_TBL, null, contentValues);
    }

    public String getGenreName(int id){
        String name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM " + GENRE_TBL + " WHERE id=?", new String[]{String.valueOf(id)});

        cursor.moveToFirst();
        return cursor.getString(0);
    }

    public int getGenreId(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM " + GENRE_TBL + " WHERE name=?", new String[]{name});

        cursor.moveToFirst();
        return cursor.getInt(0);
    }


    public ArrayList<Genre> getGenre(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + GENRE_TBL, null);

        ArrayList<Genre> list = new ArrayList<>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Genre genre = new Genre(cursor.getInt(0), cursor.getString(1));
            list.add(genre);
        }

        cursor.close();
        return list;
    }

    public void addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", book.getName());
        contentValues.put("author", book.getAuthor());
        contentValues.put("genreId", book.getGenreId());

        db.insert(BOOK_TBL, null, contentValues);
    }

    public ArrayList<Book> getBook(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BOOK_TBL, null);

        ArrayList<Book> list = new ArrayList<>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            list.add(book);
        }

        cursor.close();
        return list;
    }

    public ArrayList<Book> getBookByGenre(int genreId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BOOK_TBL + " WHERE genreId=?", new String[]{String.valueOf(genreId)});

        ArrayList<Book> list = new ArrayList<>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            Book book = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            list.add(book);
        }

        cursor.close();
        return list;
    }
}
