package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.*;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.ReviewModels;
import com.ute.sandaugiatudong.models.TypeModels;
import com.ute.sandaugiatudong.models.UserModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReviewServlet", value = "/Review/*")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Reviewseller":

                List<User> tmpUser = UserModels.findAll();
                List<User> listUser = new ArrayList<>();
                for (int i = 0; i < tmpUser.size(); i++) {
                    String[] x = tmpUser.get(i).getUsername().split("\\s");
                    StringBuilder tmp = new StringBuilder(x[x.length - 1]);
                    for (int j = 0; j < tmp.length(); j++) {
                        if (j % 2 == 1) {
                            tmp.setCharAt(j, '*');
                        }
                    }
                    String txtUsername = tmp.toString();
                    User a = new User(tmpUser.get(i).getId(), txtUsername.toString());
                    listUser.add(a);
                }
                request.setAttribute("listUser", listUser);

                int ProID = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModels.findById(ProID);
                request.setAttribute("product", product);
                ServletUtils.forward("/views/vwReview/ReviewSeller.jsp", request, response);
                break;
            case "/Reviewbidder":
                break;
            case "/Viewreview":
                int Userid = Integer.parseInt(request.getParameter("id"));

                User usertmp = UserModels.findById(Userid);


                String[] x = usertmp.getUsername().split("\\s");
                StringBuilder tmp = new StringBuilder(x[x.length - 1]);
                for (int j = 0; j < tmp.length(); j++) {
                    if (j % 2 == 1) {
                        tmp.setCharAt(j, '*');
                    }
                }
                String txtUsername = tmp.toString();
                User user = new User(Userid, usertmp.getMark(), txtUsername,usertmp.getComment());


                request.setAttribute("user", user);

                ServletUtils.forward("/views/vwReview/ViewReview.jsp", request, response);
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
                request.setCharacterEncoding("UTF-8");
                int ProID = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModels.findById(ProID);
                if (product == null) {
                    ServletUtils.forward("/views/404.jsp", request, response);
                    break;
                }
                int idSeller = product.getIdUserSell();
                int idUserCur = product.getIdUserCur();
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");

                String comment = request.getParameter("commentSeller");

                String txtLike = request.getParameter("likeSeller");
                int likefrm = Integer.parseInt(txtLike);
                int like;
                if (likefrm == 1) {
                    like = 1;
                } else {
                    like = -1;
                }

                LocalDateTime now = LocalDateTime.now();
                String txtcomment = "<p>" + "#" + u.getId() + " " + now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear() + ":" + comment + "</p>";
                Review review = new Review();
                if(u.getPermission()==1){
                    review = new Review(u.getId(), idSeller, like, txtcomment);

                    User seller = UserModels.findById(idSeller);
                    String sellerCmt = seller.getComment() + txtcomment;
                    int sellerMark = seller.getMark() + like;
                    User sellerNew = new User(seller.getId(), seller.getPermission(), sellerMark, seller.getUsername(), seller.getPassword(), seller.getEmail(), seller.getPhone(), seller.getName(), sellerCmt, seller.getDob());

                    UserModels.update1(sellerNew);
                }else{
                    review = new Review(u.getId(), idUserCur, like, txtcomment);
                    User UserCur = UserModels.findById(idUserCur);
                    String BidderCmt = UserCur.getComment() + txtcomment;
                    int sellerMark = UserCur.getMark() + like;
                    User sellerNew = new User(UserCur.getId(), UserCur.getPermission(), sellerMark, UserCur.getUsername(), UserCur.getPassword(), UserCur.getEmail(), UserCur.getPhone(), UserCur.getName(), BidderCmt, UserCur.getDob());

                    UserModels.update1(sellerNew);
                }

                ReviewModels.add(review);



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
