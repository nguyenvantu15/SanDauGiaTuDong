package com.ute.sandaugiatudong.filters;

import com.ute.sandaugiatudong.beans.Category;
import com.ute.sandaugiatudong.beans.Type;
import com.ute.sandaugiatudong.models.CategoryModels;
import com.ute.sandaugiatudong.models.TypeModels;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "navCategoryFilter",value = "/*")
public class navCategoryFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<Category> list = CategoryModels.findAll();
        request.setAttribute("CategoryWithDetails", list);

        List<Type> list1 = TypeModels.findAll();
        request.setAttribute("TypeWithDetails", list1);
        chain.doFilter(request, response);

    }
}
