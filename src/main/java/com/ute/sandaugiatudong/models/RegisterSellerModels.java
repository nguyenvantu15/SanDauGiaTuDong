package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;
public class RegisterSellerModels {
    public static List<User> listRegisterUser() {
        final String query = "select * from sandaugia.registerseller";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(User.class);
        }
    }

    public static void updatePermission(int id, int permission) {
        String sql = "update sandaugia.user set permission = :permission where id = :id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("permission", permission)
                    .executeUpdate();
        }
    }

    public static void deleteRequest(int id) {
        String sql = "delete from sandaugia.registerseller where id = :id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
