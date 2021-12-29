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

@WebServlet(name = "SearchControl", value = "/SearchControl/*")
public class SearchControlServlet extends HttpServlet {
    private String a = "0";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
//        if (path == null || path.equals("/")) {
//            path = "/Search";
//        }
        switch (path) {
            case "/Search":
                request.setCharacterEncoding("UTF-8");
                String p1 = request.getParameter("txtsearch").trim();
                String textSearch = request.getParameter("txtsearch");
                int typeSearch = Integer.parseInt(request.getParameter("searchType").trim());
                String str = "\'" + p1 + "\'";

                //list userseller
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

                List<Product> list = new ArrayList<>();

                if(typeSearch == 1){
                    switch (a) {
                        case "1":
                            list = ProductModels.findAllSearchSortPriceUp(str);
                            a = "0";
                            break;
                        case "2":
                            list = ProductModels.findAllSearchSortPriceDown(str);
                            a = "0";
                            break;
                        case "3":
                            list = ProductModels.findAllSearchSortTimeEndUp(str);
                            a = "0";
                            break;
                        case "4":
                            list = ProductModels.findAllSearchSortTimeEndDown(str);
                            a = "0";
                            break;
                        default:
                            list = ProductModels.findAllSearch(str);
                            a = "0";
                            break;
                    }
                }
                else if (typeSearch == 2){
                    switch (a) {
                        case "1":
                            list = ProductModels.findAllSearchTinyDesPriceUp(str);
                            a = "0";
                            break;
                        case "2":
                            list = ProductModels.findAllSearchTinyDesPriceDown(str);
                            a = "0";
                            break;
                        case "3":
                            list = ProductModels.findAllSearchTinyDesTimeEndUp(str);
                            a = "0";
                            break;
                        case "4":
                            list = ProductModels.findAllSearchTinyDesTimeEndDown(str);
                            a = "0";
                            break;
                        default:
                            list = ProductModels.findAllSearchTinyDes(str);
                            a = "0";
                            break;
                    }
                }
                else {
                    list = ProductModels.findAllSearchTinyDesTimeEndDown(str);
                    a = "0";
                }

                /////////////
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp = LocalDateTime.now();
                String txtTimeNow = df.format(nowTmp);

                List<DateTimeNew> listDateTimeEnd = new ArrayList<>();
                List<DateTimeNew> listDateTimeStart = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    LocalDateTime tmp = list.get(i).getTimeEnd();
                    boolean kt = nowTmp.isBefore(tmp);
                    if (kt) {
                        DateTimeNew DateTmp = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                        listDateTimeEnd.add(DateTmp);
                    } else {
                        DateTimeNew DateTmp = new DateTimeNew(nowTmp.getYear(), nowTmp.getMonthValue(), nowTmp.getDayOfMonth(), nowTmp.getHour(), nowTmp.getMinute(), nowTmp.getSecond());
                        listDateTimeEnd.add(DateTmp);
                    }

                    LocalDateTime tmp1 = list.get(i).getTimeStart();
                    DateTimeNew tmpStart = new DateTimeNew(tmp1.getYear(), tmp1.getMonthValue(), tmp1.getDayOfMonth(), tmp1.getHour(), tmp1.getMinute(), tmp1.getSecond());
                    listDateTimeStart.add(tmpStart);
                }
                request.setAttribute("listDateTimeStart", listDateTimeStart);
                request.setAttribute("listDateTimeEnd", listDateTimeEnd);
                LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);

                DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());
                request.setAttribute("timenow", timeNow);
                /////

                HttpSession session = request.getSession();
                session.setAttribute("listProductSearch", list);
                session.setAttribute("textSearch", textSearch);

                ServletUtils.forward("/views/vwProduct/FullTextSearchCate.jsp", request, response);
                break;
            default:
                ServletUtils.redirect("/Home", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getPathInfo();
        switch (path) {
            case "/Search":
                String value = request.getParameter("sortPro");
                a = value;
                String url = request.getHeader("referer");
                ServletUtils.redirect(url, request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
}
