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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchControl",value = "/SearchControl/*")
public class SearchControlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/Search";
        }
        switch (path) {
            case "/Search":
                ServletUtils.forward("/views/vwProduct/FullTextSearchCate.jsp", request, response);
                break;
            default:
                ServletUtils.redirect("/Home",request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getPathInfo();
        switch (path) {
            case "/Search":
                request.setCharacterEncoding("UTF-8");
                String p1 = request.getParameter("txtsearch").trim();
                String str = "\'" + p1 + "\'";
                List<Product> t = ProductModels.findAllSearch(str);
//                request.setAttribute("proSearch", t);

                HttpSession session = request.getSession();
                session.setAttribute("listProductSearch", t);

                ServletUtils.forward("/views/vwProduct/FullTextSearchCate.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
}
