package com.example.administrador.petshopdatabase.restAPI.modelo;

import com.example.administrador.petshopdatabase.dataset.MascotaInst;

import java.util.ArrayList;

/**
 * Created by Administrador on 04/09/2016.
 */
public class MascotaResponse {
    ArrayList<MascotaInst> mascotas;

    public ArrayList<MascotaInst> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<MascotaInst> mascotas) {
        this.mascotas = mascotas;
    }
}
