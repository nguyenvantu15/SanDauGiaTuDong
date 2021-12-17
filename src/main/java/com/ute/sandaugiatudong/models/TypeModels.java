package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class TypeModels {
    public static List<Type> findAll() {
        final String query = "select * from sandaugia.type";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Type.class);
        }
    }
}
