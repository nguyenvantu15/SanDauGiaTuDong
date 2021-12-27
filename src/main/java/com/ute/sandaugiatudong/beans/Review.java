package com.ute.sandaugiatudong.beans;

public class Review {
    private int userreview;
    private int touser;
    private int like;
    private String comment;

    public int getUserreview() {
        return userreview;
    }

    public int getTouser() {
        return touser;
    }

    public int getLike() {
        return like;
    }

    public String getComment() {
        return comment;
    }

    public Review(int userreview, int touser, int like, String comment) {
        this.userreview = userreview;
        this.touser = touser;
        this.like = like;
        this.comment = comment;
    }

    public Review() {
    }
}
