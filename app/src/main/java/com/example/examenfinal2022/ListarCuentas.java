package com.example.examenfinal2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.examenfinal2022.adapter.CuentaAdapter;
import com.example.examenfinal2022.entities.Cuenta;
import com.example.examenfinal2022.services.CuentaServicio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarCuentas extends AppCompatActivity {
    private RecyclerView rvCuenta;
    //private Button btnFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cuentas);


        rvCuenta = findViewById(R.id.rvCuenta);
        /*
        //btnFormulario=findViewById(R.id.btnFormulario);
        //btnFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CuentaActivity.class);
                startActivity(intent);
            }
        });
*/
        rvCuenta.setLayoutManager(new LinearLayoutManager(this));

        Retrofit crearRetrofit = new Retrofit.Builder()
                .baseUrl("https://6359bef538725a1746b71cf0.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CuentaServicio servise = crearRetrofit.create(CuentaServicio.class);

        servise.allCuenta().enqueue(new Callback<List<Cuenta>>() {
            @Override
            public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {

                rvCuenta.setAdapter(new CuentaAdapter(response.body(), ListarCuentas.this));

                Log.i("MAIN_APP", "Response: " + response.body().size());

            }

            @Override
            public void onFailure(Call<List<Cuenta>> call, Throwable t) {
                Log.e("MAIN_APP", t.toString());
            }
        });


    }





}