package com.ute.sandaugiatudong.models;

import com.ute.sandaugiatudong.beans.*;
import com.ute.sandaugiatudong.utils.DbUtils;
import org.sql2o.Connection;

import java.util.List;


public class ProductModels {

        public static List<Product> findAllSearch(String string) {
        String query = "select * from sandaugia.product where match(product.name) against(" + string + ")" ;

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findAll() {
        final String query = "select * from sandaugia.product";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Product.class);
        }
    }

    public static List<Category> findAllCategory() {
        final String query = "select * from sandaugia.category";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).
                    executeAndFetch(Category.class);
        }
    }

    public static void add(Product p) {
        String insertSQL = "INSERT INTO product (name, price, countAuction, idUserSell, mark, timeStart, timeEnd, idUserCur, idCat, idType, tinyDes, fullDes) VALUES (:name,:price,:countauction,:idusersell,:mark,:timestart,:timeend,:idusercur,:idcat,:idtype,:tinydes,:fulldes)";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("name",p.getName())
                    .addParameter("price",p.getPrice())
                    .addParameter("countauction",p.getCountAuction())
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

    public static int getIdCur(int id){
        final String query = "SELECT * FROM product WHERE id = :id";

        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .addParameter("id",id)
                    .executeAndFetch(Product.class);
            return list.get(0).getIdUserCur();

        }
    }

    public static List<Product> findByCat(int idCat) {
        final String query = "select * from sandaugia.product where idCat = :idCat ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idCat", idCat).executeAndFetch(Product.class);
        }
    }

    public static void updatePrice(int id, int price, int idUserCur, int countAuction){
        String insertSQL = "UPDATE product SET  price = :price, idUserCur = :idusercur, countAuction = :countAuction WHERE id = :id ";
        try (Connection con = DbUtils.getConnection()){
            con.createQuery(insertSQL)
                    .addParameter("price",price)
                    .addParameter("idusercur",idUserCur)
                    .addParameter("countAuction",countAuction)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    public static List<Product> findByIdUserSell(int id) {
        final String query = "select * from sandaugia.product where idUserSell = :id";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("id", id).executeAndFetch(Product.class);
        }
    }

    public static List<Product> findByType(int idType) {
        final String query = "select * from sandaugia.product where idType = :idType ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).addParameter("idType", idType).executeAndFetch(Product.class);
        }
    }

    public static Product findById(int id) {
        final String query = "select * from product where id= :id";

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

    public static void editProductById(Product p) {
        String sql = "UPDATE product SET name = :name, price = :price, timeStart = :timeStart, timeEnd = :timeEnd , idType = :idType, idCat = :idCat, tinyDes = :tinyDes, fullDes = :fullDes WHERE id = :id";
        try (Connection con = DbUtils.getConnection()) {
            con.createQuery(sql)
                    .addParameter("id", p.getId())
                    .addParameter("name", p.getName())
                    .addParameter("price", p.getPrice())
                    .addParameter("timeStart", p.getTimeStart())
                    .addParameter("timeEnd", p.getTimeEnd())
                    .addParameter("idType", p.getIdType())
                    .addParameter("idCat", p.getIdCat())
                    .addParameter("tinyDes", p.getTinyDes())
                    .addParameter("fullDes", p.getFullDes())
                    .executeUpdate();
        }
    }

    public static int findMaxId(){
        final String query = "SELECT * FROM product ORDER BY id DESC LIMIT 1";

        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .executeAndFetch(Product.class);
            return list.get(0).getId();

        }
    }

    public static List<Product> findTop5Price() {
        final String query = "SELECT * FROM product ORDER BY price DESC LIMIT 5";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Product.class);
        }
    }

    public static List<Product> findTop5CountAuction() {
        final String query = "SELECT * FROM product ORDER BY countAuction DESC LIMIT 5";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Product.class);
        }
    }

    public static List<Product> findTopAuctionEnd() {
        final String query = "SELECT * FROM product WHERE CURRENT_TIMESTAMP()<timeEnd AND CURRENT_TIMESTAMP()>timeStart  ORDER BY price DESC LIMIT 5";



        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Product.class);
        }
    }

    public static List<Product> findByTypeDetail(int idType, int id) {
        final String query = "select * from sandaugia.product where idType = :idType and id != :id";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idType", idType)
                    .addParameter("id", id)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortCatProPriceUp(int idCat) {
        final String query = "SELECT * FROM  product WHERE idCat = :idCat ORDER BY price ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idCat",idCat)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortCatProPriceDown(int idCat) {
        final String query = "SELECT * FROM  product WHERE idCat = :idCat ORDER BY price DESC";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idCat",idCat)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortCatProTimeEndUp(int idCat) {
        final String query = "SELECT * FROM  product WHERE idCat = :idCat ORDER BY timeEnd";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idCat",idCat)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortCatProTimeEndDown(int idCat) {
        final String query = "SELECT * FROM  product WHERE idCat = :idCat ORDER BY timeEnd DESC";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idCat",idCat)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortTypeProPriceUp(int idType) {
        final String query = "SELECT * FROM  product WHERE idType = :idType ORDER BY price";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idType",idType)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortTypeProPriceDown(int idType) {
        final String query = "SELECT * FROM  product WHERE idType = :idType ORDER BY price DESC";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idType",idType)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortTypeProTimeEndUp(int idType) {
        final String query = "SELECT * FROM  product WHERE idType = :idType ORDER BY timeEnd";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idType",idType)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> sortTypeProTimeEndDown(int idType) {
        final String query = "SELECT * FROM  product WHERE idType = :idType ORDER BY timeEnd DESC";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idType",idType)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findProAuctionBySeller(int idUserSell) {
        final String query = "SELECT * FROM  product WHERE CURRENT_TIMESTAMP()<timeEnd AND CURRENT_TIMESTAMP() >= timeStart AND idUserSell = :idUserSell";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idUserSell",idUserSell)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findProUserWin(int idUserCur) {
        final String query = "SELECT * FROM product WHERE idUserCur = :idUserCur AND CURRENT_TIMESTAMP() > timeEnd";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idUserCur",idUserCur)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findAllSearchSortPriceUp(String string) {
        String query = "select * from sandaugia.product where match(product.name) against(" + string + ") ORDER BY price";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findAllSearchSortPriceDown(String string) {
        String query = "select * from sandaugia.product where match(product.name) against(" + string + ") ORDER BY price DESC ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findAllSearchSortTimeEndUp(String string) {
        String query = "select * from sandaugia.product where match(product.name) against(" + string + ") ORDER BY timeEnd";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findAllSearchSortTimeEndDown(String string) {
        String query = "select * from sandaugia.product where match(product.name) against(" + string + ") ORDER BY timeEnd DESC";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    public static Boolean ProductIsAution(int id) {
        final String query = "SELECT * FROM product WHERE CURRENT_TIMESTAMP()>=timeStart AND CURRENT_TIMESTAMP()<timeEnd AND id = :id";

        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .addParameter("id", id)
                    .executeAndFetch(Product.class);


            if ( list.size() == 0 ){
                return false;
            }

            return true;
        }
    }

    public static List<Product> findProductSoldByUser(int idUserSell) {
        final String query = "SELECT * FROM product WHERE CURRENT_TIMESTAMP()>timeEnd AND idUserSell = :idUserSell AND idUserCur != 0";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .addParameter("idUserSell", idUserSell)
                    .executeAndFetch(Product.class);
        }
    }
    /////////////////////////////////////////////////////////////
    public static List<Product> findAllSearchTinyDes(String string) {
        String query = "select * from sandaugia.product where match(product.tinyDes) against(" + string + ")" ;

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findAllSearchTinyDesPriceUp(String string) {
        String query = "select * from sandaugia.product where match(product.tinyDes) against(" + string + ") ORDER BY price";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findAllSearchTinyDesPriceDown(String string) {
        String query = "select * from sandaugia.product where match(product.tinyDes) against(" + string + ") ORDER BY price DESC ";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    public static List<Product> findAllSearchTinyDesTimeEndUp(String string) {
        String query = "select * from sandaugia.product where match(product.tinyDes) against(" + string + ") ORDER BY timeEnd";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findAllSearchTinyDesTimeEndDown(String string) {
        String query = "select * from sandaugia.product where match(product.tinyDes) against(" + string + ") ORDER BY timeEnd DESC";

        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }

    /////////////////////////////////////////////////////////////
    public static Product checkByIdType(int idType){
        final String query = "select * from sandaugia.product where idType = :idType";
        try (Connection con = DbUtils.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .addParameter("idType", idType)
                    .executeAndFetch(Product.class);

            if (list.size() == 0) {
                return null;
            }

            return list.get(0);
        }
    }
    ///////////////////////////////////////////////////////////////
    public static List<Count> countProductOfType() {
        final String query = "SELECT sandaugia.product.idType as id , COUNT(sandaugia.product.id) AS count FROM sandaugia.product GROUP BY idType";
        try (Connection con = DbUtils.getConnection()) {
            return con.createQuery(query).executeAndFetch(Count.class);
        }
    }

}
