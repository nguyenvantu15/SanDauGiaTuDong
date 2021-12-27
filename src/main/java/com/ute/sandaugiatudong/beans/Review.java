package com.ute.sandaugiatudong.beans;

public class Review {
    private int userreview;
    private int touser;
    private int mark;
    private String comment;

    public int getUserreview() {
        return userreview;
    }

    public int getTouser() {
        return touser;
    }

    public int getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public Review(int userreview, int touser, int mark, String comment) {
        this.userreview = userreview;
        this.touser = touser;
        this.mark = mark;
        this.comment = comment;
    }

    public Review() {
    }
}
