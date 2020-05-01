package com.example.malopus;

import android.graphics.Bitmap;

public class ComicItem {

    String bookName;
    Bitmap bookimg;
    String entries;

    public ComicItem(String bookName,Bitmap bookimg, String entries)
    {
        this.bookName=bookName;
        this.bookimg=bookimg;
        this.entries=entries;
    }
    public String getbookName()
    {
        return bookName;
    }
    public Bitmap getbookimg()
    {
        return bookimg;
    }
    public String getentries(){
        return entries;
    }
}
