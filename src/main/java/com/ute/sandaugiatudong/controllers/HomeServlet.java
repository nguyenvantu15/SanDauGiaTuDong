package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.DateTimeNew;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/Home/*")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Index";
        }
        switch (path) {
            case "/Index":
                List<Product> top5Price = ProductModels.findTop5Price();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp = LocalDateTime.now();
                String txtTimeNow = df.format(nowTmp);

                List<DateTimeNew> listDateTimeEndPrice = new ArrayList<>();
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
                }
                request.setAttribute("listDateTimeEndPrice", listDateTimeEndPrice);

                List<Product> top5CountAuction = ProductModels.findTop5CountAuction();
                List<DateTimeNew> listDateTimeEndCountAuction = new ArrayList<>();

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
                }
                request.setAttribute("listDateTimeEndCountAuction", listDateTimeEndCountAuction);

                request.setAttribute("top5Price", top5Price);
                request.setAttribute("top5CountAuction", top5CountAuction);


                List<Boolean> listCheckDay = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    boolean kt = nowTmp.isBefore(top5Price.get(i).getTimeEnd());
                    if (kt) {
                        listCheckDay.add(kt);
                    }
                }
                request.setAttribute("listCheckDay", listCheckDay);
                LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);

                DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());

                request.setAttribute("timenow", timeNow);

                List<Product> top5AuctionEnd = ProductModels.findTopAuctionEnd();
                request.setAttribute("top5AuctionEnd", top5AuctionEnd);

                List<DateTimeNew> listDateTimeEndAuctionEnd = new ArrayList<>();
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
                }
                request.setAttribute("listDateTimeEndAuctionEnd", listDateTimeEndAuctionEnd);


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
