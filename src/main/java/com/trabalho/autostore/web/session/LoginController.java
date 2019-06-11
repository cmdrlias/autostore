package com.trabalho.autostore.web.session;

import com.trabalho.autostore.model.User;
import com.trabalho.autostore.service.UserService;
import com.trabalho.autostore.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @RequestMapping(value = { "/" })
    public String index(HttpServletRequest request, Model model) {
        return login(request, model);
    }

    @RequestMapping(value = { "/login" })
    public String login(HttpServletRequest request, Model model) {
        if (isCurrentAuthenticationAnonymous()) {
            if (request.getParameterMap().containsKey("error")) {
                setModalError(getMessage("message.login.incorrect.credentials"), model);
            }
            model.addAttribute("loginSection", Boolean.TRUE);
            model.addAttribute("pageTitle","Login");
            return "session/login";
        } else {
            return "redirect:/dashboard";
        }

    }

    @RequestMapping(value = { "/cadastrar" })
    public String add(Model model) {
        model.addAttribute("signinSection", Boolean.TRUE);
        model.addAttribute("pageTitle","Cadastrar");
        return "session/add-user";
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.POST)
    public String save(HttpServletRequest request, Model model) {

        String username = request.getParameter("username");
        String alias    = request.getParameter("alias");
        String password = request.getParameter("password");

        User user = new User();

        user.setUsrName(username);
        user.setUsrAlias(alias);
        user.setUsrPassword(passwordEncoder.encode(password));

        userService.add(user);

        setModalSuccess(getMessage("message.users.success.add"), model);

        return index(request, model);
    }

    @RequestMapping(value = { "/my-logout" })
    public String logout(RedirectAttributes redirectAttributes) {
        setModalWarning(getMessage("message.logout.success"), redirectAttributes);
        return "redirect:/session/login";
    }

    @RequestMapping(value = { "/expired" })
    public String expired(RedirectAttributes redirectAttributes) {
        setModalWarning(getMessage("message.session.expired.multiple.access"), redirectAttributes);
        return "redirect:/session/login";
    }
}