package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.*;
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

                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    LocalDateTime nowTmp = LocalDateTime.now();
                    String txtTimeNow = df.format(nowTmp);
                    LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);
                    DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());
                    request.setAttribute("timenow", timeNow);

                    LocalDateTime tmp = product.getTimeEnd();
                    DateTimeNew TimeEnd = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());

                    tmp = product.getTimeStart();
                    DateTimeNew TimeStart = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());

                    request.setAttribute("product", product);
                    request.setAttribute("TimeEnd", TimeEnd);
                    request.setAttribute("TimeStart", TimeStart);

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
            case "/ProductWin":
                HttpSession session2 = request.getSession();
                User Bidder1 = (User) session2.getAttribute("authUser");

                List<Product> listProWin = ProductModels.findProUserWin(Bidder1.getId());
                request.setAttribute("listProWin", listProWin);
                ServletUtils.forward("/views/vwProduct/ProductWin.jsp", request, response);
                break;
            case "/Producbidderauction":

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

                HttpSession session3 = request.getSession();
                User u3 = (User) session3.getAttribute("authUser");
                List<ProductBidderAuction> listProAndBidder = HistoryAuctionModels.findProBidderAuc(u3.getId());

                List<Product> listProAuction = new ArrayList<>();
                for (int i = 0; i < listProAndBidder.size(); i++) {
                    Product tmp = ProductModels.findById(listProAndBidder.get(i).getIdPro());
                    if(ProductModels.ProductIsAution(tmp.getId())){
                        listProAuction.add(tmp);
                    }
                }

                //thoi gian

                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp = LocalDateTime.now();
                String txtTimeNow = df.format(nowTmp);

                List<DateTimeNew> listDateTimeEnd = new ArrayList<>();
                List<DateTimeNew> listDateTimeStart = new ArrayList<>();

                for (int i = 0; i < listProAuction.size(); i++) {
                    LocalDateTime tmp = listProAuction.get(i).getTimeEnd();
                    boolean kt = nowTmp.isBefore(tmp);
                    if (kt) {
                        DateTimeNew DateTmp = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                        listDateTimeEnd.add(DateTmp);
                    } else {
                        DateTimeNew DateTmp = new DateTimeNew(nowTmp.getYear(), nowTmp.getMonthValue(), nowTmp.getDayOfMonth(), nowTmp.getHour(), nowTmp.getMinute(), nowTmp.getSecond());
                        listDateTimeEnd.add(DateTmp);
                    }

                    LocalDateTime tmp1 = listProAuction.get(i).getTimeStart();
                    DateTimeNew tmpStart = new DateTimeNew(tmp1.getYear(), tmp1.getMonthValue(), tmp1.getDayOfMonth(), tmp1.getHour(), tmp1.getMinute(), tmp1.getSecond());
                    listDateTimeStart.add(tmpStart);
                }
                request.setAttribute("listDateTimeStart", listDateTimeStart);
                request.setAttribute("listDateTimeEnd", listDateTimeEnd);
                LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);

                DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());
                request.setAttribute("timenow", timeNow);

                //thoi gian

                request.setAttribute("listProAuction",listProAuction);
                ServletUtils.forward("/views/vwAuction/ProductBidderAuction.jsp", request, response);
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
            case "/Producbidderauction":

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
