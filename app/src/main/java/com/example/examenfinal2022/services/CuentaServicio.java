package com.example.examenfinal2022.services;

import com.example.examenfinal2022.entities.Cuenta;
import com.example.examenfinal2022.entities.Moviment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CuentaServicio {

    //listar
    @GET("cuentas")
    Call<List<Cuenta>> allCuenta();

    //SAVE
    @POST("cuentas")
    Call<Void> create(@Body Cuenta cuenta);

    //UPDATE
    @PUT("cuentas/{idCuent}")
    Call<Void> update(@Body Cuenta cuenta, @Path("idCuent") int id);

    @DELETE("cuentas/{idCuent}")
    Call<Void> delete(@Path("idCuent") int id);

    @POST("/cuentas/{id}/movimientos")
    Call<Moviment> crearMovimient(@Path("id") int id, @Body Moviment moviment);



}


