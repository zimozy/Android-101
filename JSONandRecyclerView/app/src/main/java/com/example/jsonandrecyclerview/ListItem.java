package com.example.jsonandrecyclerview;

/**
 * Created by tim on 1/30/18.
 */

public class ListItem {
    private String name;
    private String realname;
    private String imageUrl;

    public ListItem(String head, String desc, String imageUrl) {
        this.name = head;
        this.realname = desc;
        this.imageUrl = imageUrl;
    }

    public ListItem(String s, String s1) {

    }

    public String getName() {
        return name;
    }

    public String getRealname() {
        return realname;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
