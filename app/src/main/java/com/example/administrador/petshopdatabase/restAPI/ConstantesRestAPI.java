package com.example.administrador.petshopdatabase.restAPI;

/**
 * Created by Administrador on 04/09/2016.
 */
public class ConstantesRestAPI {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3541212578.313d275.dac4b52e63d94ae68495553708bbf70b";
    public static final String KEY_ACCESS_TOKEN = "access_token=";



    //EndPoints
    //sustituir "self" por "::userid::" y "jus4play38" por "::usuario::"
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/{userid}/media/recent/";
    public static final String KEY_GET_INFO_USER = "users/search?q=usuario";

    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + "?" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_INFO_USER = KEY_GET_INFO_USER + "&" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String SELF_USER = "self";
    public static final String DEFAULT_USERNAME = "Jus4Play38 Profile";
}
