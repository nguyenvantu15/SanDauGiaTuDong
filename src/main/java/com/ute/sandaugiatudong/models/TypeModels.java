package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.Count;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class TypeModels {
    public static List<Type> findAll() {
        final String query = "select * from sandaugia.type ORDER BY idCat";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Type.class);
        }
    }

    public static Type findByName(String name) {
        final String query = "select * from sandaugia.type where name= :name";

        try (Connection con = DbUtils.getConnection()) {
            List<Type> list = con.createQuery(query)
                    .addParameter("name", name)
                    .executeAndFetch(Type.class);


            if ( list.size() == 0 ){
                return null;
            }

            return list.get(0);
        }
    }


    public static Type findCatIdByTypeId(int id) {
        final String query = "select * from sandaugia.type where id= :id";

        try (Connection con = DbUtils.getConnection()) {
            List<Type> list = con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetch(Type.class);


            if ( list.size() == 0 ){
                return null;
            }

            return list.get(0);
        }
    }

    public static void addNewType(int idCat, String name) {
        String insertSQL = "INSERT INTO sandaugia.type (name,idCat) VALUES (:name,:idCat)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("name",name)
                    .addParameter("idCat",idCat)
                    .executeUpdate();
        }
    }

    public static void removeProById(int id) {
        final String query = "delete from sandaugia.type where id =" + id;
        try (Connection con = DbUtils.getConnection()) {
             con.createQuery(query)
                    .executeUpdate();
        }

    }

    public static void updateTypeById(int id, String name, int idCat) {
        String sql = "UPDATE sandaugia.type SET name = :name, idCat = :idCat WHERE id = :id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", name)
                    .addParameter("idCat", idCat)
                    .executeUpdate();
        }
    }

    //Khi xoa Category kiem tra xem danh muc co chứa loại sản phảm nào không
    public static Type checkByIdCat(int idCat) {
        final String query = "select * from sandaugia.type where idCat = :idCat";
        try (Connection con = DbUtils.getConnection()) {
            List<Type> list = con.createQuery(query)
                    .addParameter("idCat", idCat)
                    .executeAndFetch(Type.class);

            if (list.size() == 0) {
                return null;
            }

            return list.get(0);
        }
    }
///////////////////////////////////////////
    public static List<Count> countTypeofCate() {
        final String query = "SELECT sandaugia.type.idCat as id , COUNT(sandaugia.type.id) AS count FROM sandaugia.type GROUP BY idCat";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Count.class);
        }
    }
}
