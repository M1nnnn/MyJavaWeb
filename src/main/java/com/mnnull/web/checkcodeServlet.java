package com.mnnull.web; /**
 * @author mnnull
 * @date 2022/11/16-16:51
 */

import com.mnnull.Utils.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/checkCodeServlet")
public class checkcodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //生成验证码
        ServletOutputStream outputStream = response.getOutputStream();
        String s = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);
        //将验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute("checkcode",s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
