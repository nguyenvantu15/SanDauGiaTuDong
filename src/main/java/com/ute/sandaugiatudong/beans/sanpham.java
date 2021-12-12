package com.ute.sandaugiatudong.beans;

import java.time.LocalDate;

public class sanpham {
    private int id;
    private String ten;
    private int gia;
    private int idNguoiBan;
    private int diem;
    private LocalDate thoiGianDang;
    private LocalDate thoiGianKetThuc;
    private int idNguoiDatGia;
    private int idDanhMuc;
    private int idLoai;

    public sanpham() {
    }

    public sanpham(int id, String ten, int gia, int idNguoiBan, int diem, LocalDate thoiGianDang, LocalDate thoiGianKetThuc, int idNguoiDatGia, int idDanhMuc, int idLoai) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.idNguoiBan = idNguoiBan;
        this.diem = diem;
        this.thoiGianDang = thoiGianDang;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.idNguoiDatGia = idNguoiDatGia;
        this.idDanhMuc = idDanhMuc;
        this.idLoai = idLoai;
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public int getGia() {
        return gia;
    }

    public int getIdNguoiBan() {
        return idNguoiBan;
    }

    public int getDiem() {
        return diem;
    }

    public LocalDate getThoiGianDang() {
        return thoiGianDang;
    }

    public LocalDate getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public int getIdNguoiDatGia() {
        return idNguoiDatGia;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public int getIdLoai() {
        return idLoai;
    }
}
