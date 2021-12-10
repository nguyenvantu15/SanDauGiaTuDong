package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.nguoiDung;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

public class nguoiDungModels {
    public static void add(nguoiDung u)
    {
        String insertSQL = "INSERT INTO user (username, password, email, phone, permission, name, dob) VALUES (:username,:password,:email,:phone,:permission,:name,:dob)";
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
}
