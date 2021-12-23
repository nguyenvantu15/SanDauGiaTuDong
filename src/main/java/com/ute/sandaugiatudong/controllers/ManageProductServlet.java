package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageProductServlet", value = "/Manage/*")
public class ManageProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Userproduct":
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");
                List <Product> listProductUser = ProductModels.findByIdUserSell(u.getId());
                request.setAttribute("ProductByUser", listProductUser);
                ServletUtils.forward("/views/vwProduct/UserUpload.jsp", request, response);

                break;
            case "/Uploadauction":
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
