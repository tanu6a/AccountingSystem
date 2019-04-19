package web.command.impl;

import entities.OrderCustomer;
import entities.User;
import services.OrderCustomerService;
import services.impl.OrderCustomerServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateOrderController implements Controller {
    private OrderCustomerService orderCustomerService = OrderCustomerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        long productId = (long)req.getAttribute("productId");
        OrderCustomer order = orderCustomerService.createOrderCustomer(user.getId(), productId, 0);

        req.setAttribute("order", order);
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}