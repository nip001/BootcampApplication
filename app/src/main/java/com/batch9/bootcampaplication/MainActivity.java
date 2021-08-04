package com.batch9.bootcampaplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.batch9.bootcampaplication.adapter.PersertaAdapter;
import com.batch9.bootcampaplication.entity.Perserta;
import com.batch9.bootcampaplication.service.ApiClient;
import com.batch9.bootcampaplication.service.PersertaInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnName,btnId,btnTambah;
    EditText editSearch;
    PersertaAdapter adapter;
    RecyclerView rvPerserta;
    PersertaInterface persertaInterface = ApiClient.getRetrofit().create(PersertaInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTambah = findViewById(R.id.btnTambah);
        btnName=findViewById(R.id.btnName);
        btnId=findViewById(R.id.btnID);
        editSearch =findViewById(R.id.editSearch);
        rvPerserta = findViewById(R.id.rv_perserta);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),TambahPerserta.class);
                startActivity(i);
            }
        });

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerSearchName(editSearch.getText().toString());
            }
        });

        btnId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerSearchID(editSearch.getText().toString());
            }
        });


        Call<ArrayList<Perserta>> call = persertaInterface.getall();
        call.enqueue(new Callback<ArrayList<Perserta>>() {
            @Override
            public void onResponse(Call<ArrayList<Perserta>> call, Response<ArrayList<Perserta>> response) {
                adapter = new PersertaAdapter(response.body(),MainActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                rvPerserta.setLayoutManager(layoutManager);
                rvPerserta.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Perserta>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    protected void handlerSearchName(String name){
        Call<ArrayList<Perserta>> call = persertaInterface.getAllByName(name);
        call.enqueue(new Callback<ArrayList<Perserta>>() {
            @Override
            public void onResponse(Call<ArrayList<Perserta>> call, Response<ArrayList<Perserta>> response) {
                adapter = new PersertaAdapter(response.body(),MainActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                rvPerserta.setLayoutManager(layoutManager);
                rvPerserta.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Perserta>> call, Throwable t) {

                System.out.println(t);
            }
        });
    }

    protected void handlerSearchID(String id){
        Call<Perserta> call = persertaInterface.getById(id);
        call.enqueue(new Callback<Perserta>() {
            @Override
            public void onResponse(Call<Perserta> call, Response<Perserta> response) {
                ArrayList<Perserta> perserta = new ArrayList<>();
                perserta.add(response.body());
                adapter = new PersertaAdapter(perserta,MainActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
                rvPerserta.setLayoutManager(layoutManager);
                rvPerserta.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Perserta> call, Throwable t) {

                System.out.println(t);
            }
        });
    }
}