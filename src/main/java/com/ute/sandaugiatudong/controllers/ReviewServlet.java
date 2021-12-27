package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.TypeModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReviewServlet", value = "/Review/*")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Reviewseller":

                int ProID = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModels.findById(ProID);
                request.setAttribute("product",product);
                ServletUtils.forward("/views/vwReview/ReviewSeller.jsp", request, response);
                break;
            case "/Reviewbidder":
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
            case "/Reviewseller":
                int ProID = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModels.findById(ProID);
                if(product == null){
                    ServletUtils.forward("/views/404.jsp", request, response);
                    break;
                }
                int idSeller = product.getIdUserSell();

                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");

                String like = request.getParameter("likeSeller");

                System.out.println(like);

                String url = request.getHeader("referer");
                if (url == null) url = "/Home";
                ServletUtils.redirect(url, request, response);
                break;
            case "/Reviewbidder":
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
}
