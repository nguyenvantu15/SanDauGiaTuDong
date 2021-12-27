package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.DateTimeNew;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.CategoryModels;
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

@WebServlet(name = "ProductServlet", value = "/Product/*")
public class ProductServlet extends HttpServlet {
    private String a = "0";//nhận biết các kiểu sort
    private String b = "0";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Category":
                int ProID = Integer.parseInt(request.getParameter("id"));
                List<Product> list = new ArrayList<>();

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

                switch (a) {
                    case "1":
                        list = ProductModels.sortCatProPriceUp(ProID);
                        a = "0";
                        break;
                    case "2":
                        list = ProductModels.sortCatProPriceDown(ProID);
                        a = "0";
                        break;
                    case "3":
                        list = ProductModels.sortCatProTimeEndUp(ProID);
                        a = "0";
                        break;
                    case "4":
                        list = ProductModels.sortCatProTimeEndDown(ProID);
                        a = "0";
                        break;
                    default:
                        list = ProductModels.findByCat(ProID);
                        a = "0";
                        break;
                }

                request.setAttribute("ProductByCategory", list);

                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp = LocalDateTime.now();
                String txtTimeNow = df.format(nowTmp);

                List<DateTimeNew> listDateTimeProByCat = new ArrayList<>();
                List<DateTimeNew> listDateTimeStartProByCat = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    LocalDateTime tmp = list.get(i).getTimeEnd();
                    boolean kt = nowTmp.isBefore(tmp);
                    if (kt) {
                        DateTimeNew DateTmp = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                        listDateTimeProByCat.add(DateTmp);
                    } else {
                        DateTimeNew DateTmp = new DateTimeNew(nowTmp.getYear(), nowTmp.getMonthValue(), nowTmp.getDayOfMonth(), nowTmp.getHour(), nowTmp.getMinute(), nowTmp.getSecond());
                        listDateTimeProByCat.add(DateTmp);
                    }

                    LocalDateTime tmp1 = list.get(i).getTimeStart();
                    DateTimeNew tmpStart = new DateTimeNew(tmp1.getYear(), tmp1.getMonthValue(), tmp1.getDayOfMonth(), tmp1.getHour(), tmp1.getMinute(), tmp1.getSecond());
                    listDateTimeStartProByCat.add(tmpStart);
                }
                request.setAttribute("listDateTimeStartProByCat", listDateTimeStartProByCat);

                request.setAttribute("listDateTimeProByCat", listDateTimeProByCat);
                LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);

                DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());

                request.setAttribute("timenow", timeNow);




                ServletUtils.forward("/views/vwProduct/Category.jsp", request, response);
                break;
            case "/Type":
                int sp = Integer.parseInt(request.getParameter("id"));
                List<Product> list1 = new ArrayList<>();

                List<User> tmpUser1 = UserModels.findAll();
                List<User> listUser1 = new ArrayList<>();
                for (int i = 0; i < tmpUser1.size(); i++) {
                    String[] x = tmpUser1.get(i).getUsername().split("\\s");
                    StringBuilder tmp = new StringBuilder(x[x.length - 1]);
                    for (int j = 0; j < tmp.length(); j++) {
                        if (j % 2 == 1) {
                            tmp.setCharAt(j, '*');
                        }
                    }
                    String txtUsername = tmp.toString();
                    User a = new User(tmpUser1.get(i).getId(), txtUsername.toString());
                    listUser1.add(a);
                }
                request.setAttribute("listUser", listUser1);

                switch (b) {
                    case "1":
                        list1 = ProductModels.sortTypeProPriceUp(sp);
                        b = "0";
                        break;
                    case "2":
                        list1 = ProductModels.sortTypeProPriceDown(sp);
                        b = "0";
                        break;
                    case "3":
                        list1 = ProductModels.sortTypeProTimeEndUp(sp);
                        b = "0";
                        break;
                    case "4":
                        list1 = ProductModels.sortTypeProTimeEndDown(sp);
                        b = "0";
                        break;
                    default:
                        list1 =  ProductModels.findByType(sp);
                        b = "0";
                        break;
                }

                request.setAttribute("ProductByType", list1);

                DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp1 = LocalDateTime.now();
                String txtTimeNow1 = df1.format(nowTmp1);

                List<DateTimeNew> listDateTimeProByType = new ArrayList<>();
                List<DateTimeNew> listDateTimeStartProByType = new ArrayList<>();
                for (int i = 0; i < list1.size(); i++) {
                    LocalDateTime tmp = list1.get(i).getTimeEnd();
                    boolean kt = nowTmp1.isBefore(tmp);
                    if (kt) {
                        DateTimeNew DateTmp = new DateTimeNew(tmp.getYear(), tmp.getMonthValue(), tmp.getDayOfMonth(), tmp.getHour(), tmp.getMinute(), tmp.getSecond());
                        listDateTimeProByType.add(DateTmp);
                    } else {
                        DateTimeNew DateTmp = new DateTimeNew(nowTmp1.getYear(), nowTmp1.getMonthValue(), nowTmp1.getDayOfMonth(), nowTmp1.getHour(), nowTmp1.getMinute(), nowTmp1.getSecond());
                        listDateTimeProByType.add(DateTmp);
                    }
                    LocalDateTime tmp1 = list1.get(i).getTimeStart();
                    DateTimeNew tmpStart = new DateTimeNew(tmp1.getYear(), tmp1.getMonthValue(), tmp1.getDayOfMonth(), tmp1.getHour(), tmp1.getMinute(), tmp1.getSecond());
                    listDateTimeStartProByType.add(tmpStart);
                }
                request.setAttribute("listDateTimeProByType", listDateTimeProByType);
                request.setAttribute("listDateTimeStartProByType", listDateTimeStartProByType);
                LocalDateTime timeNowTmp1 = LocalDateTime.parse(txtTimeNow1, df1);

                DateTimeNew timeNow1 = new DateTimeNew(timeNowTmp1.getYear(), timeNowTmp1.getMonthValue(), timeNowTmp1.getDayOfMonth(), timeNowTmp1.getHour(), timeNowTmp1.getMinute(), timeNowTmp1.getSecond());

                request.setAttribute("timenow", timeNow1);

                ServletUtils.forward("/views/vwProduct/Type.jsp", request, response);
                break;

            case "/Detail":
                int proId = Integer.parseInt(request.getParameter("id"));
                int idType = Integer.parseInt(request.getParameter("idType"));

                List<User> tmpUser2 = UserModels.findAll();
                List<User> listUser2 = new ArrayList<>();
                for (int i = 0; i < tmpUser2.size(); i++) {
                    String[] x = tmpUser2.get(i).getUsername().split("\\s");
                    StringBuilder tmp = new StringBuilder(x[x.length - 1]);
                    for (int j = 0; j < tmp.length(); j++) {
                        if (j % 2 == 1) {
                            tmp.setCharAt(j, '*');
                        }
                    }
                    String txtUsername = tmp.toString();
                    User a = new User(tmpUser2.get(i).getId(), tmpUser2.get(i).getMark(),txtUsername.toString());
                    listUser2.add(a);
                }
                request.setAttribute("listUser", listUser2);



                List<Product> listSameType = ProductModels.findByTypeDetail(idType, proId);
                Product product = ProductModels.findById(proId);

                if (product == null) {
                    ServletUtils.redirect("/Home", request, response);
                } else {
                    request.setAttribute("product", product);
                    request.setAttribute("listSameType", listSameType);

                    ServletUtils.forward("/views/vwProduct/Detail.jsp", request, response);
                }
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
            case "/Category":
                String value = request.getParameter("sortPro");
                a = value;
                String url = request.getHeader("referer");
                ServletUtils.redirect(url, request, response);
                break;
            case "/Type":
                String valueb = request.getParameter("sortPro");
                b = valueb;
                String urlb = request.getHeader("referer");
                ServletUtils.redirect(urlb, request, response);
                break;
            case "/Detail":
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

}
