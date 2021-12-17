package com.ute.sandaugiatudong.filters;


import com.ute.sandaugiatudong.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();
        int auth =(int)  session.getAttribute("auth");
        if ( auth == 0) {

            session.setAttribute("retUrl", request.getRequestURI());// lưu url trước khi logout
            ServletUtils.redirect("/Account/Login", request, (HttpServletResponse) res);
            return;
        }

        // đọc thông tin user, xem field permission để check xem user có được phép vào view hay không?
        // ...

        chain.doFilter(req, res); // cho phép request tiếp tục thực hiện
    }
}