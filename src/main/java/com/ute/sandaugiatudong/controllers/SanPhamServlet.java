package com.ute.sandaugiatudong.controllers;

import com.ute.sandaugiatudong.beans.loai;
import com.ute.sandaugiatudong.beans.sanpham;
import com.ute.sandaugiatudong.models.danhmucModels;
import com.ute.sandaugiatudong.models.sanphamModels;
import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SanPhamServlet", value = "/SanPham/*")
public class SanPhamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        switch (path) {
            case "/Danhmuc":
                int spID = Integer.parseInt(request.getParameter("id"));
                List<sanpham> list = sanphamModels.findByDanhMuc(spID);
                request.setAttribute("sanpham", list);
                ServletUtils.forward("/views/vwSanPham/DanhMuc.jsp", request, response);

                break;
            case "/Loai":
                int proID = Integer.parseInt(request.getParameter("id"));
                //Product product = ProductModels.findById(proID);
//                if (product==null) {
//                    ServletUtils.redirect("/Home", request, response);
//                } else {
//                    //request.setAttribute("product", product);
//                    ServletUtils.forward("/views/vwProduct/Detail.jsp", request, response);
//                }

                break;

            default:
                //ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
