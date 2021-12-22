package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.beans.WatchList;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class WatchListModels {

    public static void add(WatchList w)
    {
        String insertSQL = "INSERT INTO watchlist (idUser, idProduct) VALUES (:iduser,:idproduct)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("iduser",w.getIdUser())
                    .addParameter("idproduct",w.getIdProduct())
                    .executeUpdate();
        }
    }

    public static List<WatchList> findByIdUser(int idUser) {
        final String query = "SELECT idUser, idProduct FROM watchlist where idUser = :idUser";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idUser", idUser)
                    .executeAndFetch(WatchList.class);
        }
    }

    public static void delete(int iduser, int idProduct)
    {
        String insertSQL = "DELETE FROM watchlist WHERE idUser = :iduser AND idProduct = :idproduct";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("iduser",iduser)
                    .addParameter("idproduct",idProduct)
                    .executeUpdate();
        }
    }
}
