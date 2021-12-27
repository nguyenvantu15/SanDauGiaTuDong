package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class UserModels {
    public static void add(User u)
    {
        String insertSQL = "INSERT INTO sandaugia.user (username, password, email, phone, permission, name, dob) VALUES (:username,:password,:email,:phone,:permission,:name,:dob)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("username",u.getUsername())
                    .addParameter("password",u.getPassword())
                    .addParameter("email",u.getEmail())
                    .addParameter("phone",u.getPhone())
                    .addParameter("name",u.getName())
                    .addParameter("permission", u.getPermission())
                    .addParameter("dob",u.getDob())
                    .executeUpdate();
        }
    }

    public static void addRegisterSeller(User u)
    {
        String insertSQL = "INSERT INTO sandaugia.registerseller (id,username, email, phone,permission, name) VALUES (:id,:username,:email,:phone,:permission,:name)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("id",u.getId())
                    .addParameter("username",u.getUsername())
                    .addParameter("email",u.getEmail())
                    .addParameter("phone",u.getPhone())
                    .addParameter("permission", u.getPermission())
                    .addParameter("name",u.getName())
                    .executeUpdate();
        }
    }

    public static void update(User u) {
        String sql = "UPDATE sandaugia.user SET name = :name, email = :email, phone = :phone, dob = :dob , password = :password WHERE id = :id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("id", u.getId())
                    .addParameter("name", u.getName())
                    .addParameter("email", u.getEmail())
                    .addParameter("phone", u.getPhone())
                    .addParameter("dob", u.getDob())
                    .addParameter("password", u.getPassword())
                    .executeUpdate();
        }
    }

    public static void update1(User u) {
        String sql = "UPDATE user SET  username = :username, password = :password, email = :email, phone = :phone, permission = :permission, name = :name, dob = :dob, mark = :mark, comment = :comment WHERE id = :id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("id", u.getId())
                    .addParameter("username",u.getUsername())
                    .addParameter("permission",u.getPermission())
                    .addParameter("name", u.getName())
                    .addParameter("email", u.getEmail())
                    .addParameter("phone", u.getPhone())
                    .addParameter("dob", u.getDob())
                    .addParameter("password", u.getPassword())
                    .addParameter("mark",u.getMark())
                    .addParameter("comment",u.getComment())
                    .executeUpdate();
        }
    }

    public static User findById(int id){
        final String query = "select * from sandaugia.user where id = :id";
        try (Connection con = DbUtils.getConnection()) {
            List<User> list = con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetch(User.class);

            if (list.size() == 0) {
                return null;
            }

            return list.get(0);
        }
    }
    public static List<User> findAll(){
        final String query = "select * from sandaugia.user";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(User.class);

        }
    }

    public static User findByUserName(String username){
        final String query = "select * from sandaugia.user where username = :username";
        try (Connection con = DbUtils.getConnection()) {
            List<User> list = con.createQuery(query)
                    .addParameter("username", username)
                    .executeAndFetch(User.class);

            if (list.size() == 0) {
                return null;
            }

            return list.get(0);
        }
    }
}
