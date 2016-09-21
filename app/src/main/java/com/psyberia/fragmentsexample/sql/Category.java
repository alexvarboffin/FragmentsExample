package com.psyberia.fragmentsexample.sql;

import com.psyberia.fragmentsexample.R;

/**
 * Created by combo on 19.09.2016.
 */
public class Category {
    private String _title;
    private String _description;
    private int _id;


    public Category(String title, String description) {
        this._title = title;
        this._description = description;
    }

    public Category() {
    }

    public String getTitle() {
        return this._title;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getId() {
        return _id;
    }

    public int getPhoto() {
        return R.mipmap.ic_launcher;
    }
}
