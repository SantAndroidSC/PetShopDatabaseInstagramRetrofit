package com.example.administrador.petshopdatabase.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.administrador.petshopdatabase.database.ConstructorMascotasInst;
import com.example.administrador.petshopdatabase.dataset.MascotaInst;
import com.example.administrador.petshopdatabase.fragments.IRVMascotasInstagramFragmentView;
import com.example.administrador.petshopdatabase.restAPI.EndPointsAPI;
import com.example.administrador.petshopdatabase.restAPI.adapter.RestAPIAdapter;
import com.example.administrador.petshopdatabase.restAPI.modelo.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrador on 04/09/2016.
 */
public class RVMascotasInstagramFragmentPresenter implements IRVMascotasInstagramFragmentPresenter{
    private IRVMascotasInstagramFragmentView irvMascotasInstagramFragmentView;
    private Context context;
    private ConstructorMascotasInst constructorMascotasInst;
    private ArrayList<MascotaInst> mascotasInst;

    public RVMascotasInstagramFragmentPresenter(IRVMascotasInstagramFragmentView irvMascotasInstagramFragmentView, Context context, String usuarioID){
        this.irvMascotasInstagramFragmentView=irvMascotasInstagramFragmentView;
        this.context=context;
        //obtenerMascotasInstagram();
        obtenerMediosRecientes(usuarioID);
    }

    //2016-06-26
    @Override
    public void obtenerMediosRecientes(String userID) {

        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorMediaRecent();
        EndPointsAPI endPointsAPI= restAPIAdapter.establecerConexionRestAPIInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endPointsAPI.getRecentMedia(userID);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse= response.body();
                mascotasInst = mascotaResponse.getMascotas();
                mostrarMascotasInstRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Error conectanto! Inténtalo de nuevo", Toast.LENGTH_LONG).show();
                Log.e("Falló conexión WS",t.toString());
            }
        });

    }

    @Override
    public void mostrarMascotasInstRV() {
        irvMascotasInstagramFragmentView.inicializarAdaptadorInstRecyclerView(irvMascotasInstagramFragmentView.crearAdaptador(mascotasInst));
        irvMascotasInstagramFragmentView.generarGridLayout();
    }


}
