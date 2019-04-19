package web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    String MAIN_PAGE ="/WEB-INF/view/layouts/default.jspx";
    String PARTNERS ="/WEB-INF/view/partners/partner.jsp";
    String ORDERS_CUSTOMER = "/WEB-INF/view/orders/ordersCustomer.jsp";

    void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
