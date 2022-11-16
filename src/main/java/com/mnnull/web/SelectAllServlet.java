package com.mnnull.web; /**
 * @author mnnull
 * @date 2022/11/7-21:12
 */

import com.mnnull.pojo.Brand;
import com.mnnull.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SelectAll")
public class SelectAllServlet extends HttpServlet {
    //创建brand对象,完成查询,提升作用域
    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Brand> brands = brandService.selectAll();
        //存入request域中
        request.setAttribute("brands",brands);
        //转发到brand.jsp页面去
        request.getRequestDispatcher("/brand.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
