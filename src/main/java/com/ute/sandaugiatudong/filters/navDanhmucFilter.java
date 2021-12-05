package com.ute.sandaugiatudong.filters;

import com.ute.sandaugiatudong.beans.danhmuc;
import com.ute.sandaugiatudong.beans.loai;
import com.ute.sandaugiatudong.models.danhmucModels;
import com.ute.sandaugiatudong.models.loaiModels;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "navDanhmucFilter",value = "/*")
public class navDanhmucFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<danhmuc> list = danhmucModels.findAll();
        request.setAttribute("danhmucWithDetails", list);

        List<loai> list1 = loaiModels.findAll();
        request.setAttribute("loaiWithDetails", list1);
        chain.doFilter(request, response);

    }
}
