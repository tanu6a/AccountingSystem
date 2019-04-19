package web.command.impl;

import com.google.gson.Gson;
import com.sun.javafx.collections.MappingChange;
import entities.Partner;
import services.PartnerService;
import services.impl.PartnerServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SavePartnerController implements Controller {
    private PartnerService partnerService = PartnerServiceImpl.getInstance();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        try {
            Partner partnerTest = gson.fromJson(req.getReader().readLine(), Partner.class);
            Partner partner = partnerService.getByTin(partnerTest.getTin());

            if (partner != null && !(partner.getName().equals(partnerTest.getName()) &&
                    partner.getFullname().equals(partnerTest.getFullname()) &&
                    partner.getTypePartner().equals(partnerTest.getTypePartner()))) {
                partner.setName(partnerTest.getName());
                partner.setFullname(partnerTest.getFullname());
                partner.setTypePartner(partnerTest.getTypePartner());
                partnerService.update(partner);

                String json = new Gson().toJson(partner);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);

                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/frontController?command=savePartner");
                return;
//        } else {
//            resp.setHeader("errorMsg", "Invalid Partners TIN");
//            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
//            dispatcher.forward(req, resp);
//            return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
