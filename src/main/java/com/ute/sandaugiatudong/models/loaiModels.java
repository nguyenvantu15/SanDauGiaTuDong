package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.loai;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class loaiModels {
    public static List<loai> findAll() {
        final String query = "select * from loai";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(loai.class);
        }
    }
}
