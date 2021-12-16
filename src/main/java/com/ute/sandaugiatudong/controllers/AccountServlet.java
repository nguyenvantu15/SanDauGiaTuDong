package com.ute.sandaugiatudong.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ute.sandaugiatudong.beans.nguoiDung;
import com.ute.sandaugiatudong.models.nguoiDungModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AccountServlet", value = "/Account/*")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        {
            String path = request.getPathInfo();

            switch (path) {
                case "/Register":
                    ServletUtils.forward("/views/vwAccount/Register.jsp",request,response);
                    break;

                case "/Login":
                    ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
                    break;
                case "/Profile":
                    ServletUtils.forward("/views/vwAccount/Profile.jsp", request, response);
                    break;

                case "/IsAvailable":
                    String username = request.getParameter("username");
                    //truyen username vao ham tim
                    nguoiDung user = nguoiDungModels.findByUserName(username);
                    boolean isAvailable = (user == null);// user = null thi tim k thay => true

                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");

                    out.print(isAvailable);
                    out.flush();
                    break;
                default:
                    //ServletUtils.forward("/views/404.jsp",request,response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path){

            //post Register
            case "/Register":
                registerUser(request,response);
                break;

            //post Login
            case "/Login":
                loginUser(request,response);
                break;
            default:
                //ServletUtils.forward("/views/404.jsp",request,response);
                break;
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");

        String rawpawd = request.getParameter("rawpwd"); //lấy mật khẩu người dùng nhập
        //Hash mật khẩu để lưu vào database
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, rawpawd.toCharArray());

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        String dob = request.getParameter("dob");
        //parse datetime
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate ngaysinh = LocalDate.parse(dob, df);

        String phone = request.getParameter("phone");
        int permission = 0;

        nguoiDung u = new nguoiDung(0,permission,username,bcryptHashString,email,phone,name,ngaysinh);
        nguoiDungModels.add(u);

        ServletUtils.forward("/views/vwAccount/Register.jsp",request,response);

    }
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
