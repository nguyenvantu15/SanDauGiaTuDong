package com.ute.sandaugiatudong.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {
    private int id,price,idUserSell,mark,idUserCur,idCat,idType,countAuction;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String name;
    private String tinyDes,fullDes;

    public Product(int id, int price, int idUserSell, int mark, int idUserCur, int idCat, int idType, int countAuction, LocalDateTime timeStart, LocalDateTime timeEnd, String name, String tinyDes, String fullDes) {
        this.id = id;
        this.price = price;
        this.idUserSell = idUserSell;
        this.mark = mark;
        this.idUserCur = idUserCur;
        this.idCat = idCat;
        this.idType = idType;
        this.countAuction = countAuction;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.tinyDes = tinyDes;
        this.fullDes = fullDes;
    }

    public Product(int id, int price, int idType, int idCat,LocalDateTime timeStart, LocalDateTime timeEnd, String name, String tinyDes, String fullDes) {
        this.id = id;
        this.price = price;
        this.idType = idType;
        this.idCat = idCat;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.tinyDes = tinyDes;
        this.fullDes = fullDes;
    }

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

    public int getCountAuction() {
        return countAuction;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public LocalDateTime getTimeEnd() {
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

    public Product() {
    }
}
