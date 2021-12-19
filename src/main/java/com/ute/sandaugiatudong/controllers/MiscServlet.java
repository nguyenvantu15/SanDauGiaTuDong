package com.ute.sandaugiatudong.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.TypeModels;
import com.ute.sandaugiatudong.models.UserModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "MiscServlet", value = "/Misc/*")
@MultipartConfig(maxFileSize = 16177215)
public class MiscServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Add":
                ServletUtils.forward("/views/vwMisc/Add.jsp", request, response);

                break;
            case "/Edit":
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path){
            case "/Add":
                addProduct(request,response);
                break;
            case "/Login":
                break;
            default:
                ServletUtils.forward("/views/404.jsp",request,response);
                break;
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String txtprice = request.getParameter("price");
        int price = Integer.parseInt(txtprice);

        String txtProType =  request.getParameter("ProType");
        String txtIdProType = txtProType.split("\\.")[0];
        int idProType = Integer.parseInt(txtIdProType);


        String tinyDes = request.getParameter("tinyDes");


        String txtTimeStart = request.getParameter("timeStart");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime timeStart = LocalDateTime.parse(txtTimeStart, df);

        String txtTimeEnd = request.getParameter("timeEnd");
        LocalDateTime timeEnd = LocalDateTime.parse(txtTimeEnd, df);



        String fullDes = request.getParameter("fullDes");



        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("authUser");

        Type t = TypeModels.findCatIdByTypeId(idProType);

        Product p = new Product(0,price,u.getId(),0,0,t.getIdCat(),idProType,timeStart,timeEnd,name,tinyDes,fullDes);

        ProductModels.add(p);

        ServletUtils.forward("/views/vwMisc/Add.jsp",request,response);

    }

}
