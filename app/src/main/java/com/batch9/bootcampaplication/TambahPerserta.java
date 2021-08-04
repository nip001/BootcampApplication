package com.batch9.bootcampaplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.batch9.bootcampaplication.entity.Perserta;
import com.batch9.bootcampaplication.service.ApiClient;
import com.batch9.bootcampaplication.service.PersertaInterface;
import com.google.gson.Gson;

import java.io.File;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahPerserta extends AppCompatActivity {

    EditText editName, editAlamat,editNohp,editBatch;
    Button btnKamera,btnSubmit;
    ImageView imageView;
    int requestCode = 1;
    String mediaPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_perserta);
        editAlamat = findViewById(R.id.editTextAlamat);
        editBatch=findViewById(R.id.editTextBatch);
        editName=findViewById(R.id.editTextName);
        editNohp=findViewById(R.id.editTextNohp);
        btnKamera=findViewById(R.id.btnKamera);
        btnSubmit = findViewById(R.id.btnSubmit);
        imageView = findViewById(R.id.imageView2);

        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent library = new Intent(getApplicationContext(), ImageSelectActivity.class);
                library.putExtra(ImageSelectActivity.FLAG_COMPRESS,true);
                library.putExtra(ImageSelectActivity.FLAG_CAMERA,true);
                library.putExtra(ImageSelectActivity.FLAG_GALLERY,true);
                startActivityForResult(library,1);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePerserta();

                finish();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode ==requestCode && resultCode == RESULT_OK){

            mediaPath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);

            imageView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
        }
    }

    protected void savePerserta(){
        Perserta perserta = new Perserta();
        perserta.setNamaPerserta(editName.getText().toString());
        perserta.setAlamat(editAlamat.getText().toString());
        perserta.setBatch(editBatch.getText().toString());
        perserta.setNohp(editNohp.getText().toString());

        Gson gson = new Gson();
        String json = gson.toJson(perserta);

        PersertaInterface persertaInterface = ApiClient.getRetrofit().create(PersertaInterface.class);

        File file = new File(mediaPath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"),file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        RequestBody data = RequestBody.create(MediaType.parse("text/plain"),json);

        Call<String> call = persertaInterface.addPerserta(fileToUpload,data);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}