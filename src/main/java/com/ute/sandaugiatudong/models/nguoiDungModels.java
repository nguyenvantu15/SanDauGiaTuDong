package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.nguoidung;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class nguoiDungModels {
    public static void add(nguoidung u)
    {
        String insertSQL = "INSERT INTO sandaugia.nguoidung (username, password, email, phone, permission, name, dob) VALUES (:username,:password,:email,:phone,:permission,:name,:dob)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("username",u.getUsername())
                    .addParameter("password",u.getPassword())
                    .addParameter("email",u.getEmail())
                    .addParameter("phone",u.getPhone())
                    .addParameter("permission", u.getPermission())
                    .addParameter("name",u.getName())
                    .addParameter("dob",u.getDob())
                    .executeUpdate();
        }
    }
    public static nguoidung findByUsername(String username) {
        final String query = "select * from sandaugia.nguoidung where username=:username";

        try (Connection con = DbUtils.getConnection()) {
            List<nguoidung> list = con.createQuery(query)
                    .addParameter("username", username)
                    .executeAndFetch(nguoidung.class);


            if ( list.size() == 0 ){
                return null;
            }

            return list.get(0);
        }
    }
}
