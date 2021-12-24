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
                HttpSession session = request.getSession();
                int id = Integer.parseInt(request.getParameter("id"));

                Product p = ProductModels.findById(id);
                request.setAttribute("ProductEdit", p);

                List <Category> listCate = ProductModels.findAllCategory();
                request.setAttribute("listCate", listCate);

                List <Type> listType = TypeModels.findAll();
                request.setAttribute("listType", listType);

                ServletUtils.forward("/views/vwMisc/Edit.jsp", request, response);
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
            case "/Edit":
                editProduct(request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp",request,response);
                break;
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price =  Integer.parseInt(request.getParameter("price"));

        String listCate =  request.getParameter("listCate");
        String idCate = listCate.split("\\.")[0];
        int idC = Integer.parseInt(idCate);

        String ProType =  request.getParameter("ProType");
        String IdProType = ProType.split("\\.")[0];
        int idProType = Integer.parseInt(IdProType);

        String TimeStart = request.getParameter("timeStart");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String[] testT1 = TimeStart.split("T",2);
        String[] testDate1 = testT1[0].split("-",3);
        String date1 = testDate1[2] + "/" + testDate1[1] + "/" + testDate1[0];
        String time1 = date1 + " " + testT1[1];

        LocalDateTime timeStart = LocalDateTime.parse(time1, df);


        String TimeEnd = request.getParameter("timeEnd");
        String[] testT = TimeEnd.split("T",2);
        String[] testDate = testT[0].split("-",3);
        String date = testDate[2] + "/" + testDate[1] + "/" + testDate[0];
        String time = date + " " + testT[1];

        LocalDateTime timeEnd = LocalDateTime.parse(time, df);

        String fullDes = request.getParameter("fullDes");
        String tinyDes = request.getParameter("tinyDes");

        Product p = new Product(id,price,idProType,idC,timeStart,timeEnd,name,tinyDes,fullDes);
        ProductModels.editProductById(p);

        ServletUtils.redirect("/Misc/Edit?id=" + id,request,response);


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

        Product p = new Product(0,price,u.getId(),0,0,t.getIdCat(),idProType,0,timeStart,timeEnd,name,tinyDes,fullDes);

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
