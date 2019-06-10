package com.trabalho.autostore.web.users;

import com.trabalho.autostore.model.User;
import com.trabalho.autostore.service.UserService;
import com.trabalho.autostore.web.BaseController;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = { "/list" })
    public String list(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);

        configure(model);
        return "user/user-list";
    }

    @RequestMapping(value = { "/add" })
    public String add(Model model) {
        configure(model);
        return "user/user-add";
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.POST)
    public String save(HttpServletRequest request, Model model) {

        configure(model);
        return list(model);
    }

    @RequestMapping(value={"/profile"})
    public String profile(Model model, HttpSession session) {
        final User user = getLoggedUser(session);

        model.addAttribute("user", user);

        configure(model);
        return "user/user-profile";
    }

    @RequestMapping(value={"/update"})
    public String update(@RequestParam(value="id", required=true) Integer usrCode, Model model, HttpServletRequest request, HttpSession session) {
        String usrPassword = request.getParameter("password");

        User user = userService.findByUsrCode(usrCode);

        user.setUsrPassword(passwordEncoder.encode(usrPassword));

        userService.update(user);

        return profile(model, session);
    }

    @RequestMapping(value = { "/delete" })
    public String delete(@RequestParam(value="id", required=true) Integer usrCode, HttpServletRequest request, Model model) {

        // TODO
        // DELETE BY CHANGING THE USER STATUS

        return list(model);
    }

    @Override
    protected User getLoggedUser(HttpSession session) {
        return super.getLoggedUser(session);
    }

    private void configure(Model model) {
        model.addAttribute("userSection", Boolean.TRUE);
        model.addAttribute("pageTitle", getMessage("message.title.users"));
    }
}
