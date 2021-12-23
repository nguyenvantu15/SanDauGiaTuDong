package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.TypeModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "MiscServlet", value = "/Misc/*")
@MultipartConfig(
        fileSizeThreshold = 2 * 1024 * 1024 ,
        maxFileSize = 50 * 1024 * 1024,
        maxRequestSize = 50 * 1024 * 1024
)
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
        request.setCharacterEncoding("UTF-8");
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

        //them imgs
        int idFile = ProductModels.findMaxId();
        String txtIdFile = String.valueOf(idFile);

        int countImgs = 1;
        for ( Part part : request.getParts()){
            if (part.getName().equals("imgsPro")){

                String targetDir = this.getServletContext().getRealPath("public/imgs/"+txtIdFile);

                File dir = new File(targetDir);
                if(!dir.exists()){
                    dir.mkdir();
                }
                String filename = String.valueOf(countImgs) + ".jpg";
                String destination = targetDir + "/" + filename;
                part.write(destination);
                countImgs = countImgs + 1;
            }
        }


        ServletUtils.forward("/views/vwMisc/Add.jsp",request,response);

    }

}
