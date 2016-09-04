package com.example.administrador.petshopdatabase.restAPI.deseralizador;

import com.example.administrador.petshopdatabase.dataset.MascotaInst;
import com.example.administrador.petshopdatabase.restAPI.JSONKeys;
import com.example.administrador.petshopdatabase.restAPI.modelo.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Administrador on 04/09/2016.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        MascotaResponse mascotaResponse= gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JSONKeys.RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarMascoteDeJSON(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<MascotaInst> deserializarMascoteDeJSON(JsonArray mascotaResponseData){
        ArrayList<MascotaInst> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            String idFoto = mascotaResponseDataObject.get(JSONKeys.MEDIA_ID).getAsString();

            JsonObject imageJson         = mascotaResponseDataObject.getAsJsonObject(JSONKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JSONKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto               = stdResolutionJson.get(JSONKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JSONKeys.MEDIA_LIKES);
            int likesFoto = likesJson.get(JSONKeys.MEDIA_LIKES_COUNT).getAsInt();

            MascotaInst mascotaActual = new MascotaInst();
            mascotaActual.setId(idFoto);
            mascotaActual.setLikes(likesFoto);
            mascotaActual.setUrlImagen(urlFoto);
            mascotas.add(mascotaActual);

        }
        return mascotas;
    }
}
