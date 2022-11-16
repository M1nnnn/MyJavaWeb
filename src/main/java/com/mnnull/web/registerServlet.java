package com.mnnull.web; /**
 * @author mnnull
 * @date 2022/11/16-15:51
 */

import com.mnnull.pojo.User;
import com.mnnull.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {

    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取对应的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //获取用户填写的验证码
        String checkCode = request.getParameter("checkCode");
        //获取生成的验证码
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("checkcode");

       //比对
        if(!code.equalsIgnoreCase(checkCode)){
            //不允许注册
            //跳转到注册页面
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        //掉方法注册
        boolean register = userService.register(user);

        if(register){
            //注册成功，跳转页面
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else {
            //注册失败，跳转到注册页面
            request.setAttribute("register_msg","注册失败，请重新注册");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
