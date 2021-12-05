package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.danhmuc;
import com.ute.sandaugiatudong.beans.loai;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class danhmucModels {
    public static List<danhmuc> findAll() {
        final String query = "select * from danhmuc";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(danhmuc.class);
        }
    }
    public static List<loai> findByDanhMucID(int idDanhMuc) {
        final String query = "select * from loai where idDanhMuc = :idDanhMuc";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idDanhMuc", idDanhMuc).executeAndFetch(loai.class);
        }
    }
}
