package com.mnnull.web.filter; /**
 * @author mnnull
 * @date 2022/11/20-19:14
 */

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebFilter("/*")
public class loginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        //判断访问资源路径是否和登录注册相关
        String[] urls ={"/login.jsp","/imgs/","/css/","/loginServlet","register.jsp","/registerServlet","/checkCodeServlet"};
        //获取当前访问的资源路径
        String url = req.getRequestURL().toString();
        //循环遍历判断
        for (String u:urls){
            if(url.contains(u)){
                //找到了，放行
                chain.doFilter(request,response);
                return;
            }
        }


        //判断session中是否有user
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        //判断user是否为空
        if(user!=null){
            //说明登录过了
            chain.doFilter(request, response);
        }else {
            //没有登录，存储提示信息，返回登录界面
            req.setAttribute("login_msg","您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
