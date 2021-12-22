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
}
