package web.command.impl;

import entities.TypePartner;
import services.PartnerService;
import services.impl.PartnerServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PartnerController implements Controller {
    private PartnerService partnerService = PartnerServiceImpl.getInstance();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("partners", partnerService.getAll());
        req.getSession().setAttribute("typesPartners", TypePartner.values());
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
