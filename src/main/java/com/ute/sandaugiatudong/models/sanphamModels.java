package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.loai;
import com.ute.sandaugiatudong.beans.sanpham;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class sanphamModels {

    public static List<sanpham> findAll() {
        final String query = "select * from sandaugia.sanpham";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(sanpham.class);
        }
    }

    public static List<sanpham> findByDanhMuc(int idDanhMuc) {
        final String query = "select * from sandaugia.sanpham where idDanhMuc = :idDanhMuc ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idDanhMuc", idDanhMuc).executeAndFetch(sanpham.class);
        }
    }

    public static List<sanpham> findByLoai(int idLoai) {
        final String query = "select * from sandaugia.sanpham where idLoai = :idLoai ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idLoai", idLoai).executeAndFetch(sanpham.class);
        }
    }

    public static sanpham findById(int id) {
        final String query = "select * from sandaugia.sanpham where id= :id";

        try (Connection con = DbUtils.getConnection()) {
            List<sanpham> list = con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetch(sanpham.class);


            if ( list.size() == 0 ){
                return null;
            }

            return list.get(0);
        }
    }

}
