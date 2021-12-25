package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.DateTimeNew;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.models.CategoryModels;
import com.ute.sandaugiatudong.models.ProductModels;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        switch (path) {
            case "/Category":
                int ProID = Integer.parseInt(request.getParameter("id"));
                List<Product> list = ProductModels.findByCat(ProID);
                request.setAttribute("ProductByCategory", list);

                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp = LocalDateTime.now();
                String txtTimeNow = df.format(nowTmp);

                List<DateTimeNew> listDateTimeProByCat = new ArrayList<>();
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
                }
                request.setAttribute("listDateTimeProByCat", listDateTimeProByCat);
                LocalDateTime timeNowTmp = LocalDateTime.parse(txtTimeNow, df);

                DateTimeNew timeNow = new DateTimeNew(timeNowTmp.getYear(), timeNowTmp.getMonthValue(), timeNowTmp.getDayOfMonth(), timeNowTmp.getHour(), timeNowTmp.getMinute(), timeNowTmp.getSecond());

                request.setAttribute("timenow", timeNow);

                ServletUtils.forward("/views/vwProduct/Category.jsp", request, response);
                break;
            case "/Type":
                int sp = Integer.parseInt(request.getParameter("id"));
                List<Product> list1 = ProductModels.findByType(sp);
                request.setAttribute("ProductByType", list1);

                DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime nowTmp1 = LocalDateTime.now();
                String txtTimeNow1 = df1.format(nowTmp1);

                List<DateTimeNew> listDateTimeProByType = new ArrayList<>();
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
                }
                request.setAttribute("listDateTimeProByType", listDateTimeProByType);
                LocalDateTime timeNowTmp1 = LocalDateTime.parse(txtTimeNow1, df1);

                DateTimeNew timeNow1 = new DateTimeNew(timeNowTmp1.getYear(), timeNowTmp1.getMonthValue(), timeNowTmp1.getDayOfMonth(), timeNowTmp1.getHour(), timeNowTmp1.getMinute(), timeNowTmp1.getSecond());

                request.setAttribute("timenow", timeNow1);

                ServletUtils.forward("/views/vwProduct/Type.jsp", request, response);
                break;

            case "/Detail":
                int proId = Integer.parseInt(request.getParameter("id"));
                int idType = Integer.parseInt(request.getParameter("idType"));

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

    }
}
