package web.command.impl;

import services.ProductService;
import services.impl.ProductServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController implements Controller {
    private ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("products", productService.getAll());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
