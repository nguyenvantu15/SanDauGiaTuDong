package com.ute.sandaugiatudong.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.RegisterSellerModels;
import com.ute.sandaugiatudong.models.UserModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Random;

@WebServlet(name = "AdminServlet", value = "/Admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            String path = request.getPathInfo();

            switch (path) {
                case "/DowngradeSeller":
                    ServletUtils.forward("/views/vwAdminManager/DowngradeSeller.jsp", request, response);
                    break;

                case "/AddTypePro":
                    List <Category> listCate = ProductModels.findAllCategory();
                    request.setAttribute("listCate", listCate);
                    ServletUtils.forward("/views/vwAdminManager/AddTypePro.jsp", request, response);
                    break;

                case "/RemovePro":
                    ServletUtils.forward("/views/vwAdminManager/RemoveProduct.jsp", request, response);
                    break;

                default:
                    ServletUtils.forward("/views/404.jsp", request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {

            //Haj cấp seller
            case "/DowngradeSeller":
                DowngradeSeller(request, response);
                break;
            //Thêm loại sản phẩm
            case "/AddTypePro":
                AddTypePro(request, response);
                break;

            //Xóa sản phẩm
            case "/RemovePro":
                RemovePro(request, response);
                break;

            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void DowngradeSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int permission = 2;
        RegisterSellerModels.updatePermission(id, permission);
        RegisterSellerModels.deleteRequest(id);
        ServletUtils.redirect("/Account/RegisterSeller", request, response);

    }

    private void RemovePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        RegisterSellerModels.deleteRequest(id);
        ServletUtils.redirect("/Account/RegisterSeller", request, response);

    }

    private void AddTypePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        String nameNewType = request.getParameter("newtype");
        String listCate =  request.getParameter("category");
        System.out.println(nameNewType);
        String idCate = listCate.split("\\.")[0];
        int idC = Integer.parseInt(idCate);

        ProductModels.addNewType(idC,nameNewType);

        ServletUtils.redirect("/Admin/AddTypePro", request, response);

    }
}
