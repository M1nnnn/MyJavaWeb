package com.mnnull.web; /**
 * @author mnnull
 * @date 2022/11/16-11:06
 */

import com.mnnull.pojo.User;
import com.mnnull.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取记住密码复选框
        String remember = request.getParameter("remember");

        //调用service方法
        User login = userService.login(username, password);
        //判断
        if(login!=null){
            //判断用户是否勾选记住我
            if("1".equals(remember)){
                //勾选了，发送cooki

                //创建cookie对象
                Cookie C_username = new Cookie("username", username);
                Cookie C_password = new Cookie("password", password);

                //设置cookie存活时间
                C_username.setMaxAge(60*60*24);
                C_password.setMaxAge(60*60*24);

                //添加cookie
                response.addCookie(C_username);
                response.addCookie(C_password);

            }

            //将登陆成功的数据login,存储到session上
            HttpSession session = request.getSession();

            session.setAttribute("user",login);
            //登陆成功,跳转到查询所有的页面
            String contentType = request.getContextPath();
            response.sendRedirect(contentType+"/SelectAll");
        }else {
            //登陆失败

            //存储错误信息到reques
            request.setAttribute("login_msg","用户名或密码错误");
            //跳转到登录界面login.jsp
            //request的信息要靠转发才可以获取
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
