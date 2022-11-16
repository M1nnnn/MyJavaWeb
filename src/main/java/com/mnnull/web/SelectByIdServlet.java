package com.mnnull.web; /**
 * @author mnnull
 * @date 2022/11/7-23:03
 */

import com.mnnull.pojo.Brand;
import com.mnnull.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SelectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    //创建brand对象,完成查询,提升作用域
    private  BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收ID
        String id = request.getParameter("id");
        //掉方法查询
        Brand brand = brandService.selectById(Integer.parseInt(id));
        //存入request中
        request.setAttribute("brand",brand);
        //转发到修改页面
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
