package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.HistoryAuction;
import com.ute.sandaugiatudong.beans.Product;
import com.ute.sandaugiatudong.beans.User;
import com.ute.sandaugiatudong.beans.WatchList;
import com.ute.sandaugiatudong.models.*;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BehaviorServlet", value = "/Behavior/*")
public class BehaviorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        switch (path) {
            case "/Auction":
                int proId = Integer.parseInt(request.getParameter("id"));
                Product product = ProductModels.findById(proId);
                if (product == null) {
                    ServletUtils.redirect("/Home", request, response);
                } else {
                    request.setAttribute("product", product);
                    ServletUtils.forward("/views/vwAuction/Auction.jsp", request, response);
                }
                break;
            case "/watchlist":
                watchlist(request, response);
                break;
            case "/viewwatchlist":
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("authUser");
                List <WatchList> WList = WatchListModels.findByIdUser(u.getId());
                List <Product> PList = new ArrayList<Product>();
                for(int i=0;i<WList.size();i++){
                    WatchList wl= WList.get(i);
                    Product tmpPro = ProductModels.findById(wl.getIdProduct());
                    PList.add(tmpPro);
                }
                request.setAttribute("WatchListByUser", PList);
                ServletUtils.forward("/views/vwAccount/Viewwatchlist.jsp", request, response);
                break;
            case "/DeleteWatchitem":
                int ProID = Integer.parseInt(request.getParameter("id"));
                HttpSession session1 = request.getSession();
                User u1 = (User) session1.getAttribute("authUser");
                WatchListModels.delete(u1.getId(),ProID);
                String url = request.getHeader("referer");
                if (url == null) url = "/Home";
                ServletUtils.redirect(url, request, response);
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
            case "/Auction":
                doAuction(request,response);
                break;
            case "/watchlist":
                break;
            case "/viewwatchlist":
                break;
            case "/DeleteWatchitem":
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }
    private void doAuction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int proId = Integer.parseInt(request.getParameter("id"));
        String txtMaxUserPrice = request.getParameter("price");
        int maxUserPrice = Integer.parseInt(txtMaxUserPrice);

        List<HistoryAuction> historyAuctions = HistoryAuctionModels.findByIdProduct(proId);

        int priceMaxBidder=0;

        for (int i = 0; i < historyAuctions.size() ;i++){
            HistoryAuction tmp= historyAuctions.get(i);
            if(tmp.getPriceMaxBidder() > priceMaxBidder){
                priceMaxBidder = tmp.getPriceMaxBidder();
            }
        }



        if(maxUserPrice > priceMaxBidder){

        } else{

        }

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
