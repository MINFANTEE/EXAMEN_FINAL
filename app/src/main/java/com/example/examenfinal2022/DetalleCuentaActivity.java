package com.example.examenfinal2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetalleCuentaActivity extends AppCompatActivity {


    private TextView tvDetalleNombre;
    private TextView tvDetalleSaldo;
    private Button btnCrearMovi;
    private Button btnVerMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cuenta);



        tvDetalleNombre=findViewById(R.id.tvDetalleNombre);
        tvDetalleSaldo=findViewById(R.id.tvDetalleSaldo);
        btnCrearMovi=findViewById(R.id.btnCrearMovi);
        btnVerMovimientos=findViewById(R.id.btnVerMovi);

        btnVerMovimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DetalleCuentaActivity.this,VerMovimentActivity.class);
                startActivity(i);
            }
        });

        Intent intent=getIntent();

        tvDetalleNombre.setText(intent.getStringExtra("d_nombre"));
        tvDetalleSaldo.setText(intent.getStringExtra("d_saldo"));
        btnCrearMovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(DetalleCuentaActivity.this,MovimientActivity.class);

                String id=intent.getStringExtra("d_id");
                i2.putExtra("mm_id",id);
                Log.i("MAIN_APP", "nombre: " +intent.getStringExtra("d_id"));

                startActivity(i2);
            }

        });







    }
}