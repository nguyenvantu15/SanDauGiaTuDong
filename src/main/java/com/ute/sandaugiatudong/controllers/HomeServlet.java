package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.DateTimeNew;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.UserModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@WebServlet(name = "HomeServlet", value = "/Home/*")
public class HomeServlet extends HttpServlet {
    boolean kt = true;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (kt==true){
            kt = false;
            ThreadTime threadTime = new ThreadTime();
            Timer timer = new Timer();
            timer.schedule(threadTime, 0, 1000);
        }

        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }
        switch (path) {
            case "/Index":

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

                List<Product> top5Price = ProductModels.findTop5Price();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp = LocalDateTime.now();
                String txtTimeNow = df.format(nowTmp);

                List<DateTimeNew> listDateTimeEndPrice = new ArrayList<>();
                List<DateTimeNew> listDateTimeStartPrice = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    LocalDateTime tmp = top5Price.get(i).getTimeEnd();
                    boolean kt = nowTmp.isBefore(tmp);
                    if (kt) {
                        DateTimeNew DateTmp = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                        listDateTimeEndPrice.add(DateTmp);
                    } else {
                        DateTimeNew DateTmp = new DateTimeNew(nowTmp.getYear(), nowTmp.getMonthValue(), nowTmp.getDayOfMonth(), nowTmp.getHour(), nowTmp.getMinute(), nowTmp.getSecond());
                        listDateTimeEndPrice.add(DateTmp);
                    }
                    LocalDateTime tmp1 = top5Price.get(i).getTimeStart();
                    DateTimeNew tmpStart = new DateTimeNew(tmp1.getYear(), tmp1.getMonthValue(), tmp1.getDayOfMonth(), tmp1.getHour(), tmp1.getMinute(), tmp1.getSecond());
                    listDateTimeStartPrice.add(tmpStart);
                }
                request.setAttribute("listDateTimeEndPrice", listDateTimeEndPrice);
                request.setAttribute("listDateTimeStartPrice", listDateTimeStartPrice);

                List<Product> top5CountAuction = ProductModels.findTop5CountAuction();
                List<DateTimeNew> listDateTimeEndCountAuction = new ArrayList<>();
                List<DateTimeNew> listDateTimeStartCountAuction = new ArrayList<>();

                for (int i = 0; i < 5; i++) {
                    LocalDateTime tmp = top5CountAuction.get(i).getTimeEnd();
                    boolean kt = nowTmp.isBefore(tmp);
                    if (kt) {
                        DateTimeNew DateTmp = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                        listDateTimeEndCountAuction.add(DateTmp);
                    } else {
                        DateTimeNew DateTmp = new DateTimeNew(nowTmp.getYear(), nowTmp.getMonthValue(), nowTmp.getDayOfMonth(), nowTmp.getHour(), nowTmp.getMinute(), nowTmp.getSecond());
                        listDateTimeEndCountAuction.add(DateTmp);
                    }
                    LocalDateTime tmp1 = top5Price.get(i).getTimeStart();
                    DateTimeNew tmpStart = new DateTimeNew(tmp1.getYear(), tmp1.getMonthValue(), tmp1.getDayOfMonth(), tmp1.getHour(), tmp1.getMinute(), tmp1.getSecond());
                    listDateTimeStartCountAuction.add(tmpStart);
                }
                request.setAttribute("listDateTimeEndCountAuction", listDateTimeEndCountAuction);
                request.setAttribute("listDateTimeStartCountAuction", listDateTimeStartCountAuction);

                request.setAttribute("top5Price", top5Price);
                request.setAttribute("top5CountAuction", top5CountAuction);

                LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);

                DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());

                request.setAttribute("timenow", timeNow);

                List<Product> top5AuctionEnd = ProductModels.findTopAuctionEnd();
                request.setAttribute("top5AuctionEnd", top5AuctionEnd);

                List<DateTimeNew> listDateTimeEndAuctionEnd = new ArrayList<>();
                List<DateTimeNew> listDateTimeStartAuctionEnd = new ArrayList<>();
                for (int i = 0; i < top5AuctionEnd.size(); i++) {
                    LocalDateTime tmp = top5AuctionEnd.get(i).getTimeEnd();
                    boolean kt = nowTmp.isBefore(tmp);
                    if (kt) {
                        DateTimeNew DateTmp = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                        listDateTimeEndAuctionEnd.add(DateTmp);
                    } else {
                        DateTimeNew DateTmp = new DateTimeNew(nowTmp.getYear(), nowTmp.getMonthValue(), nowTmp.getDayOfMonth(), nowTmp.getHour(), nowTmp.getMinute(), nowTmp.getSecond());
                        listDateTimeEndAuctionEnd.add(DateTmp);
                    }
                    LocalDateTime tmp1 = top5Price.get(i).getTimeStart();
                    DateTimeNew tmpStart = new DateTimeNew(tmp1.getYear(), tmp1.getMonthValue(), tmp1.getDayOfMonth(), tmp1.getHour(), tmp1.getMinute(), tmp1.getSecond());
                    listDateTimeStartAuctionEnd.add(tmpStart);
                }
                request.setAttribute("listDateTimeEndAuctionEnd", listDateTimeEndAuctionEnd);
                request.setAttribute("listDateTimeStartAuctionEnd", listDateTimeStartAuctionEnd);


                ServletUtils.forward("/views/vwHome/Index.jsp", request, response);
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
