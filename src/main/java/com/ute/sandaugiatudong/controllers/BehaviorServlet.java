package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.beans.WatchList;
import com.ute.sandaugiatudong.models.ProductModels;
import com.ute.sandaugiatudong.models.UserModels;
import com.ute.sandaugiatudong.models.WatchListModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BehaviorServlet", value = "/Behavior/*")
public class BehaviorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        switch (path) {
            case "/Auction":

                break;

            case "/watchlist":

                watchlist(request, response);
                break;
            case "/viewwatchlist":

                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");
                List<WatchList> list = WatchListModels.findByIdUser(u.getId());

                request.setAttribute("WatchListByUser", list);

                ServletUtils.forward("/views/vwAccount/Login.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void watchlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idPro = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("authUser");
        int idUser = u.getId();

        WatchList w = new WatchList(idUser, idPro);

        List<WatchList> list = WatchListModels.findByIdUser(w.getIdUser());
        boolean kt = true;

        for (int i = 0; i < list.size(); i++) {
            WatchList tmp = list.get(i);
            if (tmp.getIdProduct() == w.getIdProduct()) kt=false;
        }

        if(kt)
            WatchListModels.add(w);

        String url = request.getHeader("referer");
        if (url == null) url = "/Home";
        ServletUtils.redirect(url, request, response);
    }
}
