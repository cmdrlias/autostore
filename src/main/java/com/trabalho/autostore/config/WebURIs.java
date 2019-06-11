package com.trabalho.autostore.config;

public interface WebURIs {
    public static final String[] PUBLIC_URI = {
            "/",
            "/login",
            "/my-logout",
            "/expired",
            "/js/messages",
            "/cadastrar",
            "/save"
    };

    public static final String[] PRIVATE_URI_GENERAL = {
            "/dashboard",
    };

    public static final String[] PRIVATE_URI_SYSTEM_ADMIN = {
            "/dashboard/save-comment",
            "/users",
            "/users/list",
            "/users/add",
            "/users/save",
            "/users/profile",
            "/users/update",
            "/users/delete"
    };
}
