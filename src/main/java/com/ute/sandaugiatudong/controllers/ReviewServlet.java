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
import java.util.List;

@WebServlet(name = "ReviewServlet", value = "/Review/*")
public class ReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/Reviewseller":

                int ProID = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModels.findById(ProID);
                request.setAttribute("product", product);
                ServletUtils.forward("/views/vwReview/ReviewSeller.jsp", request, response);
                break;
            case "/Reviewbidder":
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

                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");

                String txtLike = request.getParameter("likeSeller");
                int likefrm = Integer.parseInt(txtLike);
                int like;
                if (likefrm == 1) {
                    like = 1;
                } else {
                    like = -1;
                }
                String comment = request.getParameter("commentSeller");


                LocalDateTime now = LocalDateTime.now();
                String txtcomment = "<p>" + "#" + u.getId() + " " + now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear() +  ":" + comment + "</p>";
                Review review = new Review(u.getId(), idSeller, like, txtcomment);
                ReviewModels.add(review);

                User seller = UserModels.findById(idSeller);
                String sellerCmt = seller.getComment() + txtcomment;
                int sellerMark = seller.getMark() + like;
                User sellerNew = new User(seller.getId(), seller.getPermission(), sellerMark, seller.getUsername(), seller.getPassword(), seller.getEmail(), seller.getPhone(), seller.getName(), sellerCmt, seller.getDob());

                UserModels.update1(sellerNew);

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
