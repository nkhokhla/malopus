package com.example.malopus;

import java.util.Date;

public class Item {
    public final String title;
    public final String link;
    public final String summary;
    public final String imageLink;
    public final Date date;

    Item(String title, String summary, String link, String imageLink, Date date) {
        this.title = title;
        this.summary = summary;
        this.link = link;
        this.imageLink = imageLink;
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
}

