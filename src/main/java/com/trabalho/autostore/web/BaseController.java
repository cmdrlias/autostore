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
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Collection;

public class BaseController {

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
}
