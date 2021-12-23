package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.models.CategoryModels;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
                ServletUtils.forward("/views/vwProduct/Category.jsp", request, response);
                break;
            case "/Type":
                int sp = Integer.parseInt(request.getParameter("id"));
                List<Product> list1 = ProductModels.findByType(sp);
                request.setAttribute("ProductByType", list1);
                ServletUtils.forward("/views/vwProduct/Type.jsp", request, response);
                break;

//            case "/Search":
//                String name= request.getParameter("txtSearch");
//                List<Product> listSearch = ProductModels.findByType(sp);
//                request.setAttribute("listSearch", listSearch);
//                ServletUtils.forward("/views/vwProduct/FullTextSearchCate.jsp", request, response);
//                break;

            case "/Detail":
                int proId = Integer.parseInt(request.getParameter("id"));
                int idType = Integer.parseInt(request.getParameter("idType"));

                List<Product> listSameType = ProductModels.findByTypeDetail(idType,proId);

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
