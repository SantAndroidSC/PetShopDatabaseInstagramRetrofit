package com.example.administrador.petshopdatabase.restAPI.adapter;

import com.example.administrador.petshopdatabase.restAPI.ConstantesRestAPI;
import com.example.administrador.petshopdatabase.restAPI.EndPointsAPI;
import com.example.administrador.petshopdatabase.restAPI.deseralizador.MascotaDeserializador;
import com.example.administrador.petshopdatabase.restAPI.deseralizador.UsuarioInstDeserializador;
import com.example.administrador.petshopdatabase.restAPI.modelo.MascotaResponse;
import com.example.administrador.petshopdatabase.restAPI.modelo.UsuarioResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrador on 04/09/2016.
 */
public class RestAPIAdapter {
    public EndPointsAPI establecerConexionRestAPIInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsAPI.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorDatosUsuario(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class, new UsuarioInstDeserializador());
        return gsonBuilder.create();
    }
}
