package com.trabalho.autostore.web.dashboard;

import com.trabalho.autostore.service.UserService;
import com.trabalho.autostore.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private UserService userService;


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpSession session, Model model) {

        model.addAttribute("dashboardSection", Boolean.TRUE);
        model.addAttribute("pageTitle", "Dashboard");
        return "login";
    }

    @RequestMapping(value = { "/save-comment" }, method = RequestMethod.POST)
    public String saveComment(HttpServletRequest request, HttpSession session, Model model) {

        String nwsDescription = request.getParameter("description");
        int usrCode = getLoggedUser(session).getUsrCode();

        return index(session, model);
    }
}
