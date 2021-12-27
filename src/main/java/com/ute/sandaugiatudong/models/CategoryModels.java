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
            return con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetch(Category.class);
        }
    }

    public static void addNewCate(String name) {
        String insertSQL = "INSERT INTO sandaugia.category (name) VALUES (:name)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("name",name)
                    .executeUpdate();
        }
    }

    public static void removeCategory(int id) {
        final String query = "delete from sandaugia.category where id =" + id;
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(query)
                    .executeUpdate();
        }

    }
    public static void updateCategoryById(int id, String name) {
        String sql = "UPDATE sandaugia.category SET name = :name WHERE id = :id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public static Category findCateById(int id) {
        final String query = "select * from sandaugia.category where id= :id";

        try (Connection con = DbUtils.getConnection()) {
            List<Category> list = con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetch(Category.class);
            if ( list.size() == 0 ){
                return null;
            }

            return list.get(0);
        }
    }

}
