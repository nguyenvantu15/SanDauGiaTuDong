package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountServlet", value = "/Account/")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        {
//            String path = request.getPathInfo();
//
//            switch (path) {
//                case "/Register" :
//                    ServletUtils.forward("/views/vwAccount/Register.jsp",request,response);
//                    break;
//                case "/Login" :
//                    ServletUtils.forward("/views/vwAccount/Login.jsp",request,response);
//                    break;
//                case "/Profile" :
//                    ServletUtils.forward("/views/vwAccount/Profile.jsp",request,response);
//                    break;
//                default:
//                    //ServletUtils.forward("/views/404.jsp",request,response);
//                    break;
//            }
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String path = request.getPathInfo();
//        switch (path){
//
//            //post Register
//            case "/Register":
//                registerUser(request,response);
//                break;
//
//            //post Login
//            case "/Login":
//                break;
//            default:
//                //ServletUtils.forward("/views/404.jsp",request,response);
//                break;
//        }
    }
    protected void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
