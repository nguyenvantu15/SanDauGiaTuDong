package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.HistoryAuction;
import com.ute.sandaugiatudong.beans.Review;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

public class ReviewModels {
    public static void add(Review r)
    {
        String insertSQL = "INSERT INTO review (userreview, touser, mark, comment) VALUES (:userreview,:touser,:mark,:comment)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("userreview",r.getUserreview())
                    .addParameter("touser",r.getTouser())
                    .addParameter("mark",r.getMark())
                    .addParameter("comment",r.getComment())
                    .executeUpdate();
        }
    }
}
