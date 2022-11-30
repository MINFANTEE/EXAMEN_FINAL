package com.example.examenfinal2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.examenfinal2022.entities.Cuenta;
import com.example.examenfinal2022.entities.Image;
import com.example.examenfinal2022.entities.Moviment;
import com.example.examenfinal2022.factories.RetrofitFactory;
import com.example.examenfinal2022.services.CuentaServicio;
import com.example.examenfinal2022.services.ImagenService;
import com.google.gson.Gson;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CuentaActivity extends AppCompatActivity {


    public EditText etNombre;
    public EditText etSaldoIni;
    private Button btnGuardarCuenta;
    private Button btnListar;
    private Button btnPasarMoviment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);


        etNombre=findViewById(R.id.etNombre);
        etSaldoIni=findViewById(R.id.etSaldoIni);
        btnListar=findViewById(R.id.btnListar);
        btnPasarMoviment=findViewById(R.id.btnPasarMoviment);

        btnPasarMoviment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MovimientActivity.class);
                startActivity(intent);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListarCuentas.class);
                startActivity(intent);

            }
        });


        btnGuardarCuenta = findViewById(R.id.btnGuardarCuenta);
        btnGuardarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //cuenta.nombre=etNombre.getText().toString();
                //cuenta.saldoIni=etSaldoIni.getText().toString();

                Retrofit crearRetrofit = new Retrofit.Builder()
                        .baseUrl("https://6359bef538725a1746b71cf0.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                CuentaServicio cuentaServicio = crearRetrofit.create(CuentaServicio.class);
                Cuenta cuenta = new Cuenta();

                cuenta.setNombre(etNombre.getText().toString());
                cuenta.setSaldoIni(etSaldoIni.getText().toString());

                List<Moviment> moviment1 = new ArrayList<>();
                cuenta.setL_moviment(moviment1);

                cuentaServicio.create(cuenta).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Log.i("MAIN_APP","Response: "+response.code());



                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });



            }
        });



    }
}