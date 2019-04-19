package web.command.impl;

import com.google.gson.Gson;
import entities.ItemOrderCustomer;
import services.impl.ItemOrderCustomerServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EditOrderCustomerController implements Controller {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long orderId = Long.parseLong(req.getParameter("orderId"));
        List<ItemOrderCustomer> items = ItemOrderCustomerServiceImpl.getInstance().getByOrderCustomerId(orderId);

        PrintWriter writer = resp.getWriter();
        writer.print(new Gson().toJson(items));
    }
}
