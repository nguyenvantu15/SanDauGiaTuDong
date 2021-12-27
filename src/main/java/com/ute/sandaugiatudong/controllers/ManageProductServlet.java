package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.DateTimeNew;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.UserModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ManageProductServlet", value = "/Manage/*")
public class ManageProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Userproduct":
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");
                List<Product> listProductUser = ProductModels.findByIdUserSell(u.getId());
                request.setAttribute("ProductByUser", listProductUser);
                ServletUtils.forward("/views/vwProduct/UserUpload.jsp", request, response);

                break;
            case "/Uploadauction":
                break;
            case "/ViewSellerProAuction":

                HttpSession session1 = request.getSession();
                User seller = (User) session1.getAttribute("authUser");

                List<Product> listProAuctionBySeller = ProductModels.findProAuctionBySeller(seller.getId());

                List<DateTimeNew> listDateTimeEnd = new ArrayList<>();
                for (int i = 0; i < listProAuctionBySeller.size(); i++) {
                    LocalDateTime tmp = listProAuctionBySeller.get(i).getTimeEnd();
                    DateTimeNew tmpEnd = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                    listDateTimeEnd.add(tmpEnd);
                }

                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp = LocalDateTime.now();
                String txtTimeNow = df.format(nowTmp);
                LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);
                DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());

                request.setAttribute("timenow", timeNow);

                request.setAttribute("listProAuctionBySeller", listProAuctionBySeller);
                request.setAttribute("listDateTimeEnd", listDateTimeEnd);

                ServletUtils.forward("/views/vwProduct/ProductAuctionBySeller.jsp", request, response);
                break;
            case "/Productsold":
                HttpSession session2 = request.getSession();
                User seller2 = (User) session2.getAttribute("authUser");

                List<Product> listProductSold = ProductModels.findProductSoldByUser(seller2.getId());
                request.setAttribute("listProductSold",listProductSold);

                List<User> tmpUser = UserModels.findAll();
                List<User> listUser = new ArrayList<>();
                for (int i = 0; i < tmpUser.size(); i++) {
                    String[] x = tmpUser.get(i).getUsername().split("\\s");
                    StringBuilder tmp = new StringBuilder(x[x.length - 1]);
                    for (int j = 0; j < tmp.length(); j++) {
                        if (j % 2 == 1) {
                            tmp.setCharAt(j, '*');
                        }
                    }
                    String txtUsername = tmp.toString();
                    User a = new User(tmpUser.get(i).getId(), txtUsername.toString());
                    listUser.add(a);
                }
                request.setAttribute("listUser", listUser);

                ServletUtils.forward("/views/vwProduct/ProductSold.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
