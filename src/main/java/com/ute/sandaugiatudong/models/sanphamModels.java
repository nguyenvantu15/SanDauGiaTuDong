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


}
