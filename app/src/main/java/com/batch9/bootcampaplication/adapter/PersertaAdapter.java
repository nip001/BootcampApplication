package com.batch9.bootcampaplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.batch9.bootcampaplication.DetailActivity;
import com.batch9.bootcampaplication.R;
import com.batch9.bootcampaplication.entity.Perserta;
import com.batch9.bootcampaplication.service.ApiClient;
import com.batch9.bootcampaplication.service.PersertaInterface;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersertaAdapter extends RecyclerView.Adapter<PersertaAdapter.PersertaViewHolder> {

    private ArrayList<Perserta> dataPerserta;
    private Context context;

    public PersertaAdapter(ArrayList<Perserta> dataPerserta, Context context) {
        this.dataPerserta = dataPerserta;
        this.context = context;
    }

    @NonNull
    @Override
    public PersertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.perserta_bootcamp, parent, false);
        return new PersertaAdapter.PersertaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersertaViewHolder holder, int position) {
        Glide.with(context).load("http://192.168.0.109:8080/perserta/image/"+dataPerserta.get(position).getGambar()).into(holder.fotoPerserta);
        holder.txtAlamat.setText("Alamat : "+ dataPerserta.get(position).getAlamat());
        holder.txtBatch.setText("Batch : "+dataPerserta.get(position).getBatch());
        holder.txtName.setText(dataPerserta.get(position).getNamaPerserta());
        holder.txtNohp.setText("Nomor HP : "+dataPerserta.get(position).getNohp());

        holder.cvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("perserta",dataPerserta.get(position));
                context.startActivity(i);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersertaInterface persertaInterface = ApiClient.getRetrofit().create(PersertaInterface.class);
                Call<String> call = persertaInterface.deleteById(String.valueOf(dataPerserta.get(position).getId()));
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPerserta.size();
    }

    public class PersertaViewHolder extends RecyclerView.ViewHolder{
        ImageView fotoPerserta;
        TextView txtName,txtBatch,txtAlamat,txtNohp;
        Button btnDelete;
        CardView cvButton;
        public PersertaViewHolder(@NonNull View itemView){
            super(itemView);
            fotoPerserta=itemView.findViewById(R.id.fotoPerserta);
            txtAlamat=itemView.findViewById(R.id.txtAlamat);
            txtBatch=itemView.findViewById(R.id.txtBatch);
            txtName=itemView.findViewById(R.id.txtName);
            txtNohp=itemView.findViewById(R.id.txtNohp);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            cvButton=itemView.findViewById(R.id.cardButton);
        }
    }
}
