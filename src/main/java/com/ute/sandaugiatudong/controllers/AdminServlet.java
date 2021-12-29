package com.ute.sandaugiatudong.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.models.*;
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
            request.setCharacterEncoding("UTF-8");
            switch (path) {

                case "/UserManager":
                    List <User> listUser = UserModels.findAll();
                    request.setAttribute("listUser", listUser);
                    ServletUtils.forward("/views/vwAdminManager/UserManager.jsp", request, response);
                    break;
                case "/AccountDetail":
                    int idUser = Integer.parseInt(request.getParameter("id"));
                    User user = UserModels.findById(idUser);
                    request.setAttribute("AccountUser",user);
                    ServletUtils.forward("/views/vwAdminManager/DetailAccountUser.jsp", request, response);
                    break;

                case "/CategoryManager":
                    List <Category> listCategory = CategoryModels.findAll();
                    request.setAttribute("listCategory", listCategory);
                    ServletUtils.forward("/views/vwAdminManager/CategoryManager.jsp", request, response);
                    break;

                case "/AddCategory":
                    List <Category> listCateAdd = ProductModels.findAllCategory();
                    request.setAttribute("listCategory", listCateAdd);
                    ServletUtils.forward("/views/vwAdminManager/AddCategory.jsp", request, response);
                    break;

                case "/UpdateCategory":
                    int idC = Integer.parseInt(request.getParameter("id"));

                    Category cate = CategoryModels.findCateById(idC);
                    request.setAttribute("CateU",cate);
                    ServletUtils.forward("/views/vwAdminManager/UpdateCategory.jsp", request, response);
                    break;

                case "/TypeManager":
                    List <Type> listType = TypeModels.findAll();
                    request.setAttribute("listType", listType);
                    ServletUtils.forward("/views/vwAdminManager/TypeManager.jsp", request, response);
                    break;

                case "/AddType":
                    List <Category> listCate = ProductModels.findAllCategory();
                    request.setAttribute("listCate", listCate);
                    ServletUtils.forward("/views/vwAdminManager/AddTypePro.jsp", request, response);
                    break;

                case "/UpdateType":
                    List <Category> listCateUp = ProductModels.findAllCategory();
                    request.setAttribute("listCateUp", listCateUp);

                    int id = Integer.parseInt(request.getParameter("id"));
                    Type type = TypeModels.findCatIdByTypeId(id);
                    request.setAttribute("TypeU",type);

                    ServletUtils.forward("/views/vwAdminManager/UpdateType.jsp", request, response);
                    break;

//                    Kiểm tra Category nếu trong danh mục có sảm phẩm thì không cho xóa
                case "/checkCategory":
                    int idCate = Integer.parseInt(request.getParameter("id"));
                    //truyen email vao ham tim


                    Type typecheck = TypeModels.checkByIdCat(idCate);
                    boolean isAvailableCate = (typecheck == null);// user = null thi tim k thay => true
                    System.out.println(idCate);

                    PrintWriter outCate = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");

                    outCate.print(isAvailableCate);
                    outCate.flush();
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
            case "/RemoveAccount":
                RemoveAccount(request, response);
                break;
            case "/ResetPass":
                ResetPass(request,response);
                break;
            case "/AddCategory":
                AddCategory(request, response);
                break;

            case "/RemoveCate":
                RemoveCate(request, response);
                break;

            case "/UpdateCategory":
                UpdateCate(request, response);
                break;

            //Thêm loại sản phẩm
            case "/AddType":
                AddTypePro(request, response);
                break;
            //Xóa sản phẩm
            case "/RemoveType":
                RemoveType(request, response);
                break;

            case "/UpdateType":
                UpdateType(request, response);
                break;

            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void ResetPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");
        //Lấy id tài khoản cần reset
        int id = Integer.parseInt(request.getParameter("id"));
        //Lấy email để gửi thông báo
        String reMail= request.getParameter("email").trim();

        //Random pass reset
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int len = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String rawpawd = generatedString;
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, rawpawd.toCharArray());

        //Gửi mail thông báo cho người dùng
        final String sendMail = "atttsystem@gmail.com";
        final String passSendMail = "ATTT19110306";

        String mess = "The receiving system has reset your account password. \n" +
                "To log in to your account, please enter the new password:   " + rawpawd;

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
            message.setSubject("Notice to reset account password");
            message.setText(mess);
            Transport.send(message);
            System.out.println("Done RePass");
            System.out.println(rawpawd);

            //Cập nhật pass mới và database
            UserModels.resetPass(id,bcryptHashString);
        }catch (Exception e){
            System.out.println("PassWord Reset" + rawpawd);
        };

        ServletUtils.redirect("/Admin/UserManager", request, response);

    }

    private void AddCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        String nameNewcate = request.getParameter("newcate");

        CategoryModels.addNewCate(nameNewcate);

        ServletUtils.redirect("/Admin/AddCategory", request, response);

    }

    private void UpdateCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");

        System.out.println(id + name);
        CategoryModels.updateCategoryById(id,name);


        ServletUtils.redirect("/Admin/CategoryManager", request, response);

    }

    private void UpdateType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        String strCat = request.getParameter("category");
        String idCate = strCat.split("\\.")[0];
        int idC = Integer.parseInt(idCate);

        String name = request.getParameter("name");

        TypeModels.updateTypeById(id,name,idC);


        ServletUtils.redirect("/Admin/TypeManager", request, response);

    }

    private void RemoveAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        UserModels.removeUserById(id);
        ServletUtils.redirect("/Admin/UserManager", request, response);

    }

    private void RemoveCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        Type typecheck = TypeModels.checkByIdCat(id);
        boolean isAvailableCate = (typecheck == null);
        if(isAvailableCate == true) {
            CategoryModels.removeCategory(id);
            ServletUtils.redirect("/Admin/CategoryManager", request, response);
        }
        else
        {
            List <Category> listCategory = CategoryModels.findAll();
            request.setAttribute("listCategory", listCategory);
            request.setAttribute("hasErrorCate", true);
            request.setAttribute("errorMessageCate", " Danh mục có chứa sản phẩm. Không thể xóa danh mục này.");
            ServletUtils.forward("/views/vwAdminManager/CategoryManager.jsp", request, response);
        }

    }

    private void RemoveType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
       TypeModels.removeProById(id);
        ServletUtils.redirect("/Admin/TypeManager", request, response);

    }

    private void AddTypePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lay du lieu tren view xuong
        request.setCharacterEncoding("UTF-8");

        String nameNewType = request.getParameter("newtype");
        String listCate =  request.getParameter("category");
        System.out.println(nameNewType);
        String idCate = listCate.split("\\.")[0];
        int idC = Integer.parseInt(idCate);

       TypeModels.addNewType(idC,nameNewType);

        ServletUtils.redirect("/Admin/AddType", request, response);

    }
}
