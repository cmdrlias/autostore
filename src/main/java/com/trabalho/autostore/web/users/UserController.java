package com.trabalho.autostore.web.users;

import com.trabalho.autostore.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    @RequestMapping(value = { "/list" })
    public String list(HttpSession session, Model model) {
        model.addAttribute("userSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("page.title.users"));

        return "users/list";
    }
}
