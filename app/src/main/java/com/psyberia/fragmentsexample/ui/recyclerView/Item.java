package com.psyberia.fragmentsexample.ui.recyclerView;

/**
 * Created by combo on 20.09.2016.
 */

public class Item {
    private String title;
    private String subtitle;

    Item(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
