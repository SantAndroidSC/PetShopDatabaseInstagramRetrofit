package com.example.administrador.petshopdatabase.fragments;

import com.example.administrador.petshopdatabase.adapter.MascotaInstagramAdaptador;
import com.example.administrador.petshopdatabase.dataset.MascotaInst;
import com.example.administrador.petshopdatabase.dataset.UsuarioInst;

import java.util.ArrayList;

/**
 * Created by Administrador on 04/09/2016.
 */
public interface IRVMascotasInstagramFragmentView {
    public void generarGridLayout();

    public MascotaInstagramAdaptador crearAdaptador(ArrayList<MascotaInst> mascotas);

    public void inicializarAdaptadorInstRecyclerView(MascotaInstagramAdaptador adaptador);

    public UsuarioInst obtenerDatosUsuario(String nomUsuario);
}
