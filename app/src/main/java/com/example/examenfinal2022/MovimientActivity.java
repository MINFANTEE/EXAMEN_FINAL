package com.example.examenfinal2022;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.examenfinal2022.adapter.CuentaAdapter;
import com.example.examenfinal2022.entities.Cuenta;
import com.example.examenfinal2022.entities.Image;
import com.example.examenfinal2022.entities.ImagenResponse;
import com.example.examenfinal2022.entities.Moviment;
import com.example.examenfinal2022.factories.RetrofitFactory;
import com.example.examenfinal2022.services.CuentaServicio;
import com.example.examenfinal2022.services.ImagenService;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovimientActivity extends AppCompatActivity {


    Cuenta cuenta1;
    public List<Cuenta>cuentass=new ArrayList<>();
    Moviment moviment=new Moviment();
    private Spinner spTipo;
    private EditText etMotivo;
    private EditText etMonto;
    private EditText etLatitud;
    private EditText etLongitud;
    private EditText etUrl;

    private Button btnFoto;
    private Button btnGuardarMovi;
    private ImageView imgFoto;

    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimient);

        Intent intent=getIntent();

        id=intent.getStringExtra("mm_id");
        Log.i("MAIN_APP", "IDDD: " +id);



        spTipo=findViewById(R.id.spTipo);
        etMotivo=findViewById(R.id.etMotivo);
        etMonto=findViewById(R.id.etMonto);
        etLatitud=findViewById(R.id.etLatitud);
        etLongitud=findViewById(R.id.etLongitud);

        etLatitud.setText("-7.1488177");
        etLongitud.setText("-78.524288");

        etUrl=findViewById(R.id.etUrl);

        btnFoto = findViewById(R.id.btnFoto);
        btnGuardarMovi = findViewById(R.id.btnGuardarMovi);

        imgFoto = findViewById(R.id.imgFoto);
//-------------
        Retrofit crearRetrofit = new Retrofit.Builder()
                .baseUrl("https://6359bef538725a1746b71cf0.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CuentaServicio servise = crearRetrofit.create(CuentaServicio.class);

        servise.allCuenta().enqueue(new Callback<List<Cuenta>>() {
            @Override
            public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {
                cuentass=response.body();


            }

            @Override
            public void onFailure(Call<List<Cuenta>> call, Throwable t) {
                Log.e("MAIN_APP", t.toString());
            }
        });
        //intent=getIntent();

        for (Cuenta c: cuentass){
            if (id.equals(c.id)){
                Log.i("MAIN_APP", "nombre: " +c.nombre);

            }
        }

        //--------------

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_movi, android.R.layout.simple_spinner_dropdown_item);

        spTipo.setAdapter(adapter);


        String cuentJson = intent.getStringExtra("CUENT_DAT");
        Log.i("MAIN_APP", new Gson().toJson(cuentJson));

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                    abrirCamara();
                }
                else{
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, 100);
                }

            }
        });

        btnGuardarMovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarCuenta();
            }
        });

    }

    private void guardarCuenta( ) {

        spTipo.getSelectedItem();
        moviment.motivo = etMotivo.getText().toString();
        moviment.monto = etMonto.getText().toString();
        moviment.latitud = etLatitud.getText().toString();
        moviment.longitud =etLongitud.getText().toString();
        moviment.image = etUrl.getText().toString();

        Log.i("MAIN_ACTIVITY", new Gson().toJson(moviment));

        if (moviment.id == 0) {
            callCreateAPI(cuenta1);
        }

    }

    private void callCreateAPI(Cuenta cuenta1) {

        Retrofit crearRetrofit = new Retrofit.Builder()
                .baseUrl("https://6359bef538725a1746b71cf0.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CuentaServicio service = crearRetrofit.create(CuentaServicio.class);
        service.crearMovimient(cuenta1.id, moviment).enqueue(new Callback<Moviment>() {
            @Override
            public void onResponse(Call<Moviment> call, Response<Moviment> response) {
                Log.i("MAIN_APP","Response: "+response.code());
            }

            @Override
            public void onFailure(Call<Moviment> call, Throwable t) {

            }
        });

    }



    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1000 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgFoto.setImageBitmap(imageBitmap);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();

            String encriptar = Base64.encodeToString(byteArray, Base64.DEFAULT);

            //COPY--------------------------------------------------------------------------------------
            Retrofit retrofit = new RetrofitFactory(this)
                    .build("https://api.imgur.com/", "Client-ID 8bcc638875f89d9");

            ImagenService imageService = retrofit.create(ImagenService.class);
            Image image = new Image();
            image.image= encriptar;

            imageService.enviarFoto(image).enqueue(new Callback<ImagenResponse>() {
                @Override
                public void onResponse(Call<ImagenResponse> call, Response<ImagenResponse> response) {
                    ImagenResponse res = response.body();

                    etUrl.setText(res.data.link);
                    SaveUrl();
                }
                @Override
                public void onFailure(Call<ImagenResponse> call, Throwable t) {

                }
            });

        }


    }

    private void SaveUrl() {
        Retrofit retro2 = new RetrofitFactory(MovimientActivity.this)
                .build("https://api.imgur.com/", "Client-ID 8bcc638875f89d9");

        CuentaServicio s = retro2.create(CuentaServicio.class);

        s.create(new Cuenta()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}