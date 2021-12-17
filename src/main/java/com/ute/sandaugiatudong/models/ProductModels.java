package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.Product;
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
