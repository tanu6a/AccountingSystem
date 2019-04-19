package web.command.impl;

import entities.OrderCustomer;
import entities.User;
import services.OrderCustomerService;
import services.PartnerService;
import services.impl.OrderCustomerServiceImpl;
import services.impl.PartnerServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderCustomerController implements Controller {
    private OrderCustomerService orderCustomerService = OrderCustomerServiceImpl.getInstance();
    private PartnerService partnerService = PartnerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<OrderCustomer> orders = orderCustomerService.getByUserId(user.getId());

        req.setAttribute("ordersCustomer", orders);
        req.setAttribute("partners", partnerService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);

    }
}
