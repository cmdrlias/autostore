package com.trabalho.autostore.config;

public interface WebURIs {
    public static final String[] PUBLIC_URI = {
            "/",
            "/login",
            "/my-logout",
            "/expired",
            "/js/messages",
            "/add",
            "/save"
    };

    public static final String[] PRIVATE_URI_GENERAL = {
            "/dashboard",
            "/users",
            "/users/list",
            "/users/delete",
            "/users/edit",
            "/vehicles",
            "/vehicles/list",
            "/vehicles/add",
            "/vehicles/edit",
            "/vehicles/save",
            "/vehicles/delete",
            "/vehicles/search",
            "/vehicles/details"
    };
}
