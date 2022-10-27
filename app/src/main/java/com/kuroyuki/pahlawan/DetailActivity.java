package com.kuroyuki.pahlawan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    private ImageView ivfoto;
    private TextView tvnama, tvtentang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivfoto = findViewById(R.id.iv_foto);
        tvnama = findViewById(R.id.tv_nama);
        tvtentang = findViewById(R.id.tv_tentang);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varNama");
        String tentang = intent.getStringExtra("varTentang");
        String foto = intent.getStringExtra("varFoto");

        setTitle(nama);
        tvnama.setText(nama);
        tvtentang.setText(tentang);
        Glide.with(this)
                .load(foto)
                .into(ivfoto);
    }
}