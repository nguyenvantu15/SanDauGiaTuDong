package com.ute.sandaugiatudong.beans;

public class Count {
    private int id;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Count(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public Count() {
    }
}
