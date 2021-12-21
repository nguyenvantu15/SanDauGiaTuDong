package com.ute.sandaugiatudong.beans;

public class WatchList {
    private int idUser;
    private int idProduct;

    public int getIdUser() {
        return idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public WatchList(int idUser, int idProduct) {
        this.idUser = idUser;
        this.idProduct = idProduct;
    }

    public WatchList() {
    }
}
