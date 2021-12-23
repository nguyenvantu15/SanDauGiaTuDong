package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.HistoryAuction;
import com.ute.sandaugiatudong.beans.WatchList;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class HistoryAuctionModels {
    public static List<HistoryAuction> findByIdProduct(int idPro) {
        final String query = "SELECT * FROM sandaugia.historyauction where idPro = :idPro";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idPro", idPro)
                    .executeAndFetch(HistoryAuction.class);
        }
    }
}
