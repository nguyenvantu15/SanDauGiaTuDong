package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class CategoryModels {
    public static List<Category> findAll() {
        final String query = "select * from sandaugia.category";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Category.class);
        }
    }
    public static List<Category> findByCatID(int id) {
        final String query = "select * from sandaugia.category where id = :id";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idD", id).executeAndFetch(Category.class);
        }
    }


}
