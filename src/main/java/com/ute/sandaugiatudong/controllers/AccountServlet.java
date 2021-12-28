package com.ute.sandaugiatudong.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.RegisterSellerModels;
import com.ute.sandaugiatudong.models.UserModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

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

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
                case "/ConfirmOTP":
                    ServletUtils.forward("/views/vwAccount/ConfirmOTP.jsp",request,response);
                    break;

                case "/Login":
                    ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
                    break;
                case "/Profile":

                    ServletUtils.forward("/views/vwAccount/Profile.jsp", request, response);
                    break;

                case "/Update":

                    int id = -1;
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                    }catch (NumberFormatException e){

                    }
                    User u = UserModels.findById(id);
                    if (u!=null) {
                        request.setAttribute("userFind",u); //Gui thong tin ra ngoai
                        ServletUtils.forward("/views/vwAccount/Update.jsp", request, response);
                    }
                    else {
                        ServletUtils.redirect("/Account/Profile",request,response);
                    }
                    break;

                case "/RegisterSeller":
                    List<User> list = RegisterSellerModels.listRegisterUser();

                    request.setAttribute("ListRegisterSeller", list);
                    ServletUtils.forward("/views/vwAccount/RegisterSeller.jsp", request, response);
                    break;

                case "/IsAvailable":
                    String username = request.getParameter("username");
                    //truyen username vao ham tim
                    User user = UserModels.findByUserName(username);
                    boolean isAvailable = (user == null);// user = null thi tim k thay => true

                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");

                    out.print(isAvailable);
                    out.flush();
                    break;

                default:
                    ServletUtils.forward("/views/404.jsp",request,response);
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
                try {
                    sendOTP(request,response);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                registerUser(request,response);
                break;

            case "/ConfirmOTP":
                confirmOTP(request,response);
                break;

            //post Login
            case "/Login":
                loginUser(request,response);
                break;

            case "/Logout":
                logout(request, response);
                break;

            case "/Profile":
                registerSeller(request, response);
                break;

            case "/Update":
                updatePro(request, response);
                break;

            case "/Accept":
                updateSeller(request,response);
                break;

            case "/Delete":
                deleteSellerRe(request,response);
                break;


            default:
                ServletUtils.forward("/views/404.jsp",request,response);
                break;
        }
    }


    private void confirmOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("userRegister");
        String otp = (String) session.getAttribute("codeOTP");
        String userOTP = request.getParameter("OTP").trim();

        int OTP = Integer.parseInt(otp);
        int uOTP = Integer.parseInt(userOTP);

        if (OTP == uOTP)
        {
            UserModels.add(u);
            ServletUtils.redirect("/Account/Register",request,response);
        }
        else {
            request.setAttribute("hasErrorOTP", true);
            request.setAttribute("errorMessageOTP", "Mã OTP Không chính xác");

            ServletUtils.forward("/views/vwAccount/ConfirmOTP.jsp", request, response);
        }


    }

    private void sendOTP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        request.setCharacterEncoding("UTF-8");
        String reMail= request.getParameter("email").trim();
        Random ran = new Random();
        int OTP = ran.nextInt((999999 - 100000) + 1) + 100000;

        HttpSession session1 = request.getSession();
        session1.setAttribute("codeOTP", String.valueOf(OTP));

        final String sendMail = "atttsystem@gmail.com";
        final String passSendMail = "ATTT19110306";

        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(prop,new javax.mail.Authenticator(){
            protected  PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(sendMail,passSendMail);
            }
        });
        // Đăng nhập được email
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sendMail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(reMail)
            );
            message.setSubject("Code OTP");
            message.setText(String.valueOf(OTP));
            Transport.send(message);
        }catch (Exception e){

        }


    }

    private void deleteSellerRe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        RegisterSellerModels.deleteRequest(id);
        ServletUtils.redirect("/Account/RegisterSeller", request, response);

    }

    private void registerSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int  permission = Integer.parseInt(request.getParameter("permission"));

        User u = new User(id,permission,username,email,phone,name);
        UserModels.addRegisterSeller(u);

        ServletUtils.forward("/views/vwAccount/Profile.jsp",request,response);

    }

    private void updateSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int permission = 2;
        RegisterSellerModels.updatePermission(id,permission);
        RegisterSellerModels.deleteRequest(id);
        ServletUtils.redirect("/Account/RegisterSeller", request, response);

    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

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

        int permission = 1;

        User u = new User(0,permission,username,bcryptHashString,email,phone,name,ngaysinh);

        HttpSession session = request.getSession();
        session.setAttribute("userRegister", u);
        ServletUtils.redirect("/Account/ConfirmOTP",request,response);
    }

    private void updatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id =Integer.parseInt( request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        //format dob de lua vao database
        String ngaySinh = request.getParameter("dob");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate dob = LocalDate.parse(ngaySinh, df);

        //So sanh mat khau cu
        User user = UserModels.findById(id);
        String password = request.getParameter("oldpass");
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        //Thay doi mat khau
        String newpass = request.getParameter("newpass");
        //Hash mật khẩu để lưu vào database
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, newpass.toCharArray());


        HttpSession session = request.getSession();
        if (result.verified) {

            User u = new User(id,email,name,phone,dob,bcryptHashString);
            UserModels.update(u);

            //Dang suat account sau khi chinh sua
            session.setAttribute("auth", 0);
            session.setAttribute("authUser", new User());
            ServletUtils.redirect("/Account/Login", request, response);

        } else {

            request.setAttribute("hasError", true);
            request.setAttribute("errorMessage", "Invalid login");
            ServletUtils.redirect(String.format("/Account/Update?id=" + id), request, response);
        }

    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = UserModels.findByUserName(username);
        if (user != null) {
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
            if (result.verified) {
                // session là biến dữ liệu dùng chung cho tất cả request của một phiên làm việc
                HttpSession session = request.getSession();
                //
                session.setAttribute("auth", user.getPermission());
                session.setAttribute("authUser", user);
                String url = (String) (session.getAttribute("retUrl"));
                if (url == null)
                    url = "/Home";

                ServletUtils.redirect(url, request, response);
            } else {
                request.setAttribute("hasError", true);
                request.setAttribute("errorMessage", "Invalid login");

                ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
            }
        } else {
            request.setAttribute("hasError", true);
            request.setAttribute("errorMessage", "Invalid login");

            ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("auth", 0);
        session.setAttribute("authUser", new User());
        String url = request.getHeader("referer");
        if (url == null)
            url = "/Home";
        ServletUtils.redirect(url, request, response);
    }
}
