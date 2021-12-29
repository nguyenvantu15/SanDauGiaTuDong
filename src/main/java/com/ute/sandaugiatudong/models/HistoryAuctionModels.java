package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.HistoryAuction;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.ProductBidderAuction;
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
    public static List<HistoryAuction> findByIdProduct1(int idPro) {
        final String query = "SELECT DISTINCT idPro,idBidder FROM sandaugia.historyauction where idPro = :idPro";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idPro", idPro)
                    .executeAndFetch(HistoryAuction.class);
        }
    }

    public static List<ProductBidderAuction> findProBidderAuc(int idBidder) {
        final String query = "SELECT DISTINCT idPro,idBidder FROM historyauction WHERE idBidder= :idBidder";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idBidder", idBidder)
                    .executeAndFetch(ProductBidderAuction.class);
        }
    }
    public static void deleteByIdPro(int idPro)
    {
        String insertSQL = "DELETE FROM historyauction WHERE idPro= :idPro";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("idPro",idPro)
                    .executeUpdate();
        }
    }
}
