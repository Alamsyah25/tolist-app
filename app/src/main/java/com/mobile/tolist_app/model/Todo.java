package com.mobile.tolist_app.model;

public class Todo {
    int id;
    String title;
    String date;
    String time;

    public Todo(int id, String title, String date, String time) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
