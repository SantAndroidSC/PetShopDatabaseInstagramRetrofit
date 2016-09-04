package com.example.administrador.petshopdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrador.petshopdatabase.dataset.UsuarioInst;
import com.example.administrador.petshopdatabase.restAPI.EndPointsAPI;
import com.example.administrador.petshopdatabase.restAPI.adapter.RestAPIAdapter;
import com.example.administrador.petshopdatabase.restAPI.modelo.UsuarioResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar myActionBar = (Toolbar) findViewById(R.id.myToolbar);
        myActionBar.setTitle("Setting Account...");
        setSupportActionBar(myActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void callInstagram(View view){
        //Intent intent = new Intent(this, InstagramActivity.class);
        //startActivity(intent);

        EditText edtUsuario = (EditText) findViewById(R.id.txtInstUser);
        if(edtUsuario.getText().toString().trim().length() >0) {
            final String nomUsuario = edtUsuario.getText().toString();

            //Toast.makeText(PedirUsuarioInstagram.this, "usuario_nomUsuario: "+edtUsuario.getText().toString(), Toast.LENGTH_SHORT).show();


            //busca los datos del usuario en el WebService

            RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
            Gson gsonDatosUsuario = restAPIAdapter.construyeGsonDeserializadorDatosUsuario();
            EndPointsAPI endPointsAPI = restAPIAdapter.establecerConexionRestAPIInstagram(gsonDatosUsuario);
            Call<UsuarioResponse> usuarioResponseCall = endPointsAPI.getDatosUsuario(nomUsuario);
            usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                @Override
                public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                    String nuevoUsuario = "";
                    String nuevoID = "";
                    String nuevaURL = "";

                    UsuarioResponse usuarioResponse = response.body(); //data JSON
                    UsuarioInst usuarioInst = usuarioResponse.getUsuarioInst();
                    Intent intent = new Intent(SettingActivity.this, MainActivity.class);

//                    if (usuarioInst.getId().trim().length()>0) {
//                        Toast.makeText(getBaseContext(), "El usuario " + nomUsuario + " existe", Toast.LENGTH_LONG).show();
                    nuevoUsuario = usuarioInst.getFullName();
                    nuevoID = usuarioInst.getId();
                    nuevaURL = usuarioInst.getUrlFotoPerfil();
//                    } else {
//                        Toast.makeText(getBaseContext(), "El usuario " + nomUsuario + " no existe", Toast.LENGTH_LONG).show();
//                        nuevoUsuario = "";
//                        nuevoID = "self";
//                        nuevaURL = "";
//                    }
                    intent.putExtra("usuario", nuevoUsuario);
                    intent.putExtra("usuarioID", nuevoID);
                    intent.putExtra("usuarioURL", nuevaURL);

                    startActivity(intent);
                    //Toast.makeText(PedirUsuarioInstagram.this, nuevoUsuario+" - "+nuevoID, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                    Toast toast=Toast.makeText(getBaseContext(), "¡Error conectando! ¿Existe el usuario?", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    //Log.e("Falló conexión WS", t.toString());
                }
            });

            //Toast.makeText(PedirUsuarioInstagram.this, "Hay usuario", Toast.LENGTH_LONG).show();
        }else{
            Toast toast=Toast.makeText(SettingActivity.this, "Hay que poner un usuario", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

    }
}
