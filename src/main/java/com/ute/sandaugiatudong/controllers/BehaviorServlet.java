package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.HistoryAuction;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.beans.WatchList;
import com.ute.sandaugiatudong.models.*;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BehaviorServlet", value = "/Behavior/*")
public class BehaviorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        switch (path) {
            case "/Auction":
                int proId = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModels.findById(proId);
                if (product == null) {
                    ServletUtils.redirect("/Home", request, response);
                } else {
                    request.setAttribute("product", product);
                    ServletUtils.forward("/views/vwAuction/Auction.jsp", request, response);
                }
                break;
            case "/watchlist":
                watchlist(request, response);
                break;
            case "/viewwatchlist":
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");
                List<WatchList> WList = WatchListModels.findByIdUser(u.getId());
                List<Product> PList = new ArrayList<Product>();
                for (int i = 0; i < WList.size(); i++) {
                    WatchList wl = WList.get(i);
                    Product tmpPro = ProductModels.findById(wl.getIdProduct());
                    PList.add(tmpPro);
                }
                request.setAttribute("WatchListByUser", PList);
                ServletUtils.forward("/views/vwAccount/Viewwatchlist.jsp", request, response);
                break;
            case "/DeleteWatchitem":
                int ProID = Integer.parseInt(request.getParameter("id"));
                HttpSession session1 = request.getSession();
                User u1 = (User) session1.getAttribute("authUser");
                WatchListModels.delete(u1.getId(), ProID);
                String url = request.getHeader("referer");
                if (url == null) url = "/Home";
                ServletUtils.redirect(url, request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        switch (path) {
            case "/Auction":
                doAuction(request, response);
                break;
            case "/watchlist":
                break;
            case "/viewwatchlist":
                break;
            case "/DeleteWatchitem":
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void doAuction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int proId = Integer.parseInt(request.getParameter("id"));

        String txtMaxUserPrice = request.getParameter("price");
        int maxUserPrice = Integer.parseInt(txtMaxUserPrice);

        Product product = ProductModels.findById(proId);

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("authUser");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime nowTmp = LocalDateTime.now(); //thoi gian chua format
        String txtTimeNow = df.format(nowTmp);
        LocalDateTime timeNow = LocalDateTime.parse(txtTimeNow, df);

        //list lịch sử đấy giá sản phẩm trước khi bidder đấu giá
        List<HistoryAuction> historyAuctions = HistoryAuctionModels.findByIdProduct(proId);

        //nếu chưa có ai đấu giá
        if (historyAuctions.size() == 0) {
            HistoryAuction rowNew = new HistoryAuction(proId, u.getId(), maxUserPrice, product.getPrice(), u.getId(), timeNow);
            HistoryAuctionModels.add(rowNew);

            ProductModels.updatePrice(proId, product.getPrice(), u.getId(), product.getCountAuction() + 1);

            String url = request.getHeader("referer");
            if (url == null) url = "/Home";
            ServletUtils.redirect(url, request, response);
        } else {
            int priceMaxBidder = 0;

            //tìm max PriceMaxBidder

            for (int i = 0; i < historyAuctions.size(); i++) {
                HistoryAuction tmp = historyAuctions.get(i);
                if (tmp.getPriceMaxBidder() > priceMaxBidder) {
                    priceMaxBidder = tmp.getPriceMaxBidder();
                }
            }


            int priceInPlush;
            if (maxUserPrice > priceMaxBidder) {
                int diff = maxUserPrice - priceMaxBidder;
                if (10000000 <= diff) {
                    priceInPlush = 500000;
                } else if (1000000 <= diff) {
                    priceInPlush = 100000;
                } else if (500000 <= diff) {
                    priceInPlush = 50000;
                } else if (100000 <= diff) {
                    priceInPlush = 20000;
                } else if (10000 <= diff) {
                    priceInPlush = 10000;
                } else {
                    priceInPlush = 1000;
                }
                HistoryAuction rowNew = new HistoryAuction(proId, u.getId(), maxUserPrice, priceMaxBidder + priceInPlush, u.getId(), timeNow);

                HistoryAuctionModels.add(rowNew);
                ProductModels.updatePrice(proId, priceMaxBidder + priceInPlush, u.getId(), product.getCountAuction() + 1);

                String url = request.getHeader("referer");
                if (url == null) url = "/Home";
                ServletUtils.redirect(url, request, response);
            } else {
                // tìm hàng có giá vào max
                int idBidderCur = ProductModels.getIdCur(proId);
                HistoryAuction rowNew = new HistoryAuction(proId, u.getId(), maxUserPrice, maxUserPrice, idBidderCur, timeNow);

                HistoryAuctionModels.add(rowNew);
                ProductModels.updatePrice(proId, maxUserPrice, idBidderCur, product.getCountAuction() + 1);

                String url = request.getHeader("referer");
                if (url == null) url = "/Home";
                ServletUtils.redirect(url, request, response);
            }
        }


    }

    private void watchlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPro = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("authUser");
        int idUser = u.getId();

        WatchList w = new WatchList(idUser, idPro);

        List<WatchList> list = WatchListModels.findByIdUser(w.getIdUser());
        boolean kt = true;

        for (int i = 0; i < list.size(); i++) {
            WatchList tmp = list.get(i);
            if (tmp.getIdProduct() == w.getIdProduct()) kt = false;
        }

        if (kt)
            WatchListModels.add(w);

        String url = request.getHeader("referer");
        if (url == null) url = "/Home";
        ServletUtils.redirect(url, request, response);
    }
}
