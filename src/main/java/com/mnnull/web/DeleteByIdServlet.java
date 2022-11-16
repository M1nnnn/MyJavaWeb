package com.mnnull.web; /**
 * @author mnnull
 * @date 2022/11/8-0:28
 */

import com.mnnull.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {
    private   BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
        //获取ID
        String id= request.getParameter("id");

        //调方法删除
        brandService.delete(Integer.parseInt(id));

        //3.调转到重新查询的servlet
        request.getRequestDispatcher("/SelectAll").forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
