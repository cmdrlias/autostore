package com.trabalho.autostore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BaseService {
    @Autowired
    MessageSource messageSource;

    /**
     * Retorna a mensagem com chave <code>
     * @param code
     * @return
     */
    protected String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * Retorna a mensagem com chave <code> e argumentos <args>
     * @param code
     * @param args
     * @return
     */
    protected String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
