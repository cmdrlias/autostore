package com.trabalho.autostore.web;

import com.trabalho.autostore.model.User;
import com.trabalho.autostore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Collection;

public class BaseController {

    private static final String MODAL_SUCCESS = "success";
    private static final String MODAL_WARNING = "warning";
    private static final String MODAL_ERROR = "error";
    private static final String MODAL_INFO = "info";

    @Lazy
    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Value("${server.servlet.session.timeout}")
    private String sessionTimeout;

    @ModelAttribute("sessionTimeout")
    protected long getSessionTimeOut() {
        return Duration.parse("PT" + sessionTimeout).toMinutes() * 60;
    }

    protected String getPrincipal() {
        String userName = null;
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @ModelAttribute("userName")
    public String getUserName(HttpSession session) {
        final User user = getLoggedUser(session);
        if (user != null) {
            return user.getUsrName();
        }
        return getPrincipal();
    }

    protected User getLoggedUser(HttpSession session) {
        final String principal = getPrincipal();
        if (!isCurrentAuthenticationAnonymous()) {
            if (session.getAttribute("userName") != null) {
                return (User) session.getAttribute("userName");
            }

            final User user = userService.findByUsrName(principal);
            session.setAttribute("userName", user);
            return user;
        }

        return null;
    }

    protected String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    protected String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    @ModelAttribute("isAnonymous")
    protected boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication == null || authenticationTrustResolver.isAnonymous(authentication));
    }

    @ModelAttribute("isSystemAdmin")
    public boolean isSystemAdmin() {
        return hasAuthority("ROLE_SYSTEM_ADMIN");
    }

    private boolean hasAuthority(String role) {
        if (!isCurrentAuthenticationAnonymous()) {
            final Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (final GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setModalSuccess(String text, Model model) {
        setModal(getMessage("message.success"), text, model, MODAL_SUCCESS);
    }

    public void setModalWarning(String text, Model model) {
        setModal(getMessage("message.warning"), text, model, MODAL_WARNING);
    }

    public void setModalError(String text, Model model) {
        setModal(getMessage("message.error"), text, model, MODAL_ERROR);
    }

    public void setModalInfo(String text, Model model) {
        setModal(getMessage("message.info"), text, model, MODAL_INFO);
    }

    public void setModal(String title, String text, Model model) {
        setModal(title, text, model, null);
    }

    private void setModal(String title, String text, Model model, String icone) {
        model.addAttribute("modalTitle", title);
        model.addAttribute("modalText", text);
        model.addAttribute("modalIcon", icone);
        model.addAttribute("modalButton", getMessage("label.button.close"));
    }

    public void setModalSuccess(String text, RedirectAttributes redirectAttributes) {
        setModal(getMessage("message.success"), text, redirectAttributes, MODAL_SUCCESS);
    }

    public void setModalWarning(String text, RedirectAttributes redirectAttributes) {
        setModal(getMessage("message.warning"), text, redirectAttributes, MODAL_WARNING);
    }

    public void setModalError(String text, RedirectAttributes redirectAttributes) {
        setModal(getMessage("message.error"), text, redirectAttributes, MODAL_ERROR);
    }

    public void setModalInfo(String text, RedirectAttributes redirectAttributes) {
        setModal(getMessage("message.info"), text, redirectAttributes, MODAL_INFO);
    }

    public void setModal(String title, String text, RedirectAttributes redirectAttributes) {
        setModal(title, text, redirectAttributes, null);
    }

    private void setModal(String title, String text, RedirectAttributes redirectAttributes, String icone) {
        redirectAttributes.addFlashAttribute("modalTitle", title);
        redirectAttributes.addFlashAttribute("modalText", text);
        redirectAttributes.addFlashAttribute("modalIcon", icone);
        redirectAttributes.addFlashAttribute("modalButton", getMessage("label.button.close"));
    }

}
