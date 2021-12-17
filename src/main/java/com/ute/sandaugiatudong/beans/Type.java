package com.ute.sandaugiatudong.beans;

public class Type {
    private int id;
    private int idCat;
    private String name;

    public int getId() {
        return id;
    }

    public int getIdCat() {
        return idCat;
    }

    public String getName() {
        return name;
    }

    public Type(int id, int idCat, String name) {
        this.id = id;
        this.idCat = idCat;
        this.name = name;
    }

    public Type() {
    }
}
