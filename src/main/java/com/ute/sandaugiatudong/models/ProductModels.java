package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;

public class ProductModels {
    public static List<Product> findAll() {
        final String query = "select * from sandaugia.product";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Product.class);
        }
    }

    public static void add(Product p)
    {
        String insertSQL = "INSERT INTO product (name, price, idUserSell, mark, timeStart, timeEnd, idUserCur, idCat, idType, tinyDes, fullDes) VALUES (:name,:price,:idusersell,:mark,:timestart,:timeend,:idusercur,:idcat,:idtype,:tinydes,:fulldes)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("name",p.getName())
                    .addParameter("price",p.getPrice())
                    .addParameter("idusersell",p.getIdUserSell())
                    .addParameter("mark",p.getMark())
                    .addParameter("timestart", p.getTimeStart())
                    .addParameter("timeend",p.getTimeEnd())
                    .addParameter("idusercur",p.getIdUserCur())
                    .addParameter("idcat",p.getIdCat())
                    .addParameter("idtype",p.getIdType())
                    .addParameter("tinydes",p.getTinyDes())
                    .addParameter("fulldes",p.getFullDes())
                    .executeUpdate();
        }
    }

    public static List<Product> findByCat(int idCat) {
        final String query = "select * from sandaugia.product where idCat = :idCat ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idCat", idCat).executeAndFetch(Product.class);
        }
    }

    public static List<Product> findByType(int idType) {
        final String query = "select * from sandaugia.product where idType = :idType ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idType", idType).executeAndFetch(Product.class);
        }
    }

    public static Product findById(int id) {
        final String query = "select * from sandaugia.product where id= :id";

        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetch(Product.class);


            if ( list.size() == 0 ){
                return null;
            }

            return list.get(0);
        }
    }
}
