package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.HistoryAuction;
import com.ute.sandaugiatudong.beans.WatchList;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class HistoryAuctionModels {

    public static void add(HistoryAuction h)
    {
        String insertSQL = "INSERT INTO historyauction (idPro, idBidder, priceMaxBidder, PriceIn, bidderCur, time) VALUES (:idpro,:idbidder,:pricemaxbidder,:pricein,:biddercur,:time)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("idpro",h.getIdPro())
                    .addParameter("idbidder",h.getIdBidder())
                    .addParameter("pricemaxbidder",h.getPriceMaxBidder())
                    .addParameter("pricein",h.getPriceIn())
                    .addParameter("biddercur",h.getBidderCur())
                    .addParameter("time",h.getTime())
                    .executeUpdate();
        }
    }

    public static List<HistoryAuction> findByIdProduct(int idPro) {
        final String query = "SELECT * FROM sandaugia.historyauction where idPro = :idPro";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idPro", idPro)
                    .executeAndFetch(HistoryAuction.class);
        }
    }
}
