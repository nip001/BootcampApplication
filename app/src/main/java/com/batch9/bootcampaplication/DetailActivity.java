package com.batch9.bootcampaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.batch9.bootcampaplication.entity.Perserta;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ImageView fotoDetail;
    TextView txtName,txtAlamat,txtNohp,txtBatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fotoDetail = findViewById(R.id.detailFoto);
        txtAlamat = findViewById(R.id.txtDetailAlamat);
        txtName = findViewById(R.id.txtDetailNama);
        txtBatch = findViewById(R.id.txtDetailBatch);
        txtNohp = findViewById(R.id.txtDetailNohp);

        Perserta perserta = getIntent().getParcelableExtra("perserta");

        Glide.with(DetailActivity.this).load("http://192.168.0.109:8080/perserta/image/"+perserta.getGambar()).into(fotoDetail);
        txtNohp.setText(perserta.getNohp());
        txtName.setText(String.valueOf(perserta.getId()));
        txtAlamat.setText(perserta.getAlamat());
        txtBatch.setText(perserta.getBatch());


    }
}