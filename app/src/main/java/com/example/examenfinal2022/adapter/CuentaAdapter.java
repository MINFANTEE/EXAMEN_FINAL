package com.example.examenfinal2022.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal2022.DetalleCuentaActivity;
import com.example.examenfinal2022.ListarCuentas;
import com.example.examenfinal2022.R;
import com.example.examenfinal2022.entities.Cuenta;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CuentaAdapter extends RecyclerView.Adapter{

    List<Cuenta> dataCuenta;
    Context context;

    public CuentaAdapter(List<Cuenta> data,Context context){
        this.context=context;
        this.dataCuenta = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView =inflater.inflate(R.layout.item_cuenta,parent,false);
        return new CuentaViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Cuenta cuenta = dataCuenta.get(position);

        //ImageView imPelicula = holder.itemView.findViewById(R.id.imPelicula);
        TextView tvNombre = holder.itemView.findViewById(R.id.tvNombre);
        TextView tvSaldoIni = holder.itemView.findViewById(R.id.tvSaldoIni);

        //TextView tvLongitud=holder.itemView.findViewById(R.id.tvLongitud);
        //TextView tvLatidud=holder.itemView.findViewById(R.id.tvLatidud);


        Button btnVerDetalle=holder.itemView.findViewById(R.id.btnDetalle);

        //Picasso.get().load(pelicula.image).into(imPelicula);
        tvNombre.setText(cuenta.nombre);
        tvSaldoIni.setText(String.valueOf(cuenta.saldoIni));
        btnVerDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i= new Intent(context, DetalleCuentaActivity.class);
                i.putExtra("d_nombre",cuenta.nombre);
                i.putExtra("d_saldo",cuenta.saldoIni);
                i.putExtra("d_id",cuenta.id);

                Log.i("MAIN_APP", "ids: "+cuenta.id+cuenta.nombre );




                context.startActivity(i);

            }
        });



        //tvLongitud.setText(pelicula.longitud);
        //tvLatidud.setText(pelicula.latitud);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(holder.itemView.getContext(), ListarCuentas.class);
                intent.putExtra("CUENT_DAT", new Gson().toJson(cuenta));

                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataCuenta.size();
    }

    public class CuentaViewHolder extends RecyclerView.ViewHolder{

        public CuentaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}