package web.command.impl;

import entities.OrderSupplier;
import entities.User;
import services.OrderSupplierService;
import services.PartnerService;
import services.impl.OrderSupplierServiceImpl;
import services.impl.PartnerServiceImpl;
import web.command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderSupplierController implements Controller {
    private OrderSupplierService orderSupplierService = OrderSupplierServiceImpl.getInstance();
    private PartnerService partnerService = PartnerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<OrderSupplier> orders = orderSupplierService.getByUserId(user.getId());

        req.setAttribute("ordersSupplier", orders);
        req.setAttribute("partners", partnerService.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(req, resp);
    }
}
