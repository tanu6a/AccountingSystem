package web.command.impl;

import com.google.gson.Gson;
import entities.Partner;
import services.PartnerService;
import services.impl.PartnerServiceImpl;
import web.command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPartnerController implements Controller {
    private PartnerService partnerService = PartnerServiceImpl.getInstance();


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        try {
            Partner partner = gson.fromJson(req.getReader().readLine(), Partner.class);

            Partner partnerNew = partnerService.createPartner(partner.getTin(), partner.getName(), partner.getFullname(), partner.getTypePartner());


                String contextPath = req.getContextPath();
                resp.sendRedirect(contextPath + "/frontController?command=partners");
                return;
//        } else {
//            resp.setHeader("errorMsg", "Invalid Partners TIN");
//            RequestDispatcher dispatcher = req.getRequestDispatcher(MAIN_PAGE);
//            dispatcher.forward(req, resp);
//            return;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
