package com.batch9.bootcampaplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Perserta implements Parcelable {
    @SerializedName("id")
    private long id;
    @SerializedName("namaPerserta")
    private String namaPerserta;
    @SerializedName("batch")
    private String batch;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("nohp")
    private String nohp;
    @SerializedName("gambar")
    private String gambar;

    public Perserta(long id, String namaPerserta, String batch, String alamat, String nohp, String gambar) {
        this.id = id;
        this.namaPerserta = namaPerserta;
        this.batch = batch;
        this.alamat = alamat;
        this.nohp = nohp;
        this.gambar = gambar;
    }

    public Perserta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaPerserta() {
        return namaPerserta;
    }

    public void setNamaPerserta(String namaPerserta) {
        this.namaPerserta = namaPerserta;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.namaPerserta);
        dest.writeString(this.batch);
        dest.writeString(this.alamat);
        dest.writeString(this.nohp);
        dest.writeString(this.gambar);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readLong();
        this.namaPerserta = source.readString();
        this.batch = source.readString();
        this.alamat = source.readString();
        this.nohp = source.readString();
        this.gambar = source.readString();
    }

    protected Perserta(Parcel in) {
        this.id = in.readLong();
        this.namaPerserta = in.readString();
        this.batch = in.readString();
        this.alamat = in.readString();
        this.nohp = in.readString();
        this.gambar = in.readString();
    }

    public static final Parcelable.Creator<Perserta> CREATOR = new Parcelable.Creator<Perserta>() {
        @Override
        public Perserta createFromParcel(Parcel source) {
            return new Perserta(source);
        }

        @Override
        public Perserta[] newArray(int size) {
            return new Perserta[size];
        }
    };
}
