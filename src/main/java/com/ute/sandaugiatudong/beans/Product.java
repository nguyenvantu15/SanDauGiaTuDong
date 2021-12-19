package com.ute.sandaugiatudong.beans;

import java.time.LocalDate;

public class Product {
    private int id,price,idUserSell,mark,idUserCur,idCat,idType;
    private LocalDate timeStart;
    private LocalDate timeEnd;
    private String name;
    private String tinyDes,fullDes;

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getIdUserSell() {
        return idUserSell;
    }

    public int getMark() {
        return mark;
    }

    public int getIdUserCur() {
        return idUserCur;
    }

    public int getIdCat() {
        return idCat;
    }

    public int getIdType() {
        return idType;
    }

    public LocalDate getTimeStart() {
        return timeStart;
    }

    public LocalDate getTimeEnd() {
        return timeEnd;
    }

    public String getName() {
        return name;
    }

    public String getTinyDes() {
        return tinyDes;
    }

    public String getFullDes() {
        return fullDes;
    }

    public Product(int id, int price, int idUserSell, int mark, int idUserCur, int idCat, int idType, LocalDate timeStart, LocalDate timeEnd, String name, String tinyDes, String fullDes) {
        this.id = id;
        this.price = price;
        this.idUserSell = idUserSell;
        this.mark = mark;
        this.idUserCur = idUserCur;
        this.idCat = idCat;
        this.idType = idType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.tinyDes = tinyDes;
        this.fullDes = fullDes;
    }

    public Product() {
    }
}
