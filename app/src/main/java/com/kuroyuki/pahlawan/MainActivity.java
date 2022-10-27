package com.kuroyuki.pahlawan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvpahlawan;
    private ArrayList<ModelPahlawan> data = new ArrayList<>();

    private int mview = 0; //0 = mode card, 1 = mode grid
    static final String STATE_MODE = "MODE_VIEW";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvpahlawan = findViewById(R.id.rv_pahlawan);
        rvpahlawan.setHasFixedSize(true);

        data.addAll(DataPahlawan.ambildatapahlawan());
        if(savedInstanceState != null){
            mview = savedInstanceState.getInt(STATE_MODE);
            if(mview == 0){
                tampilDataCard();
            }
            else{
                tampildatagrid();
            }

        }
        else{
            tampilDataCard();
        }
        //ubah layout
        //tampilDataCard();
        tampildatagrid();
    }

    private void tampilDataCard(){
        mview = 0;
        rvpahlawan.setLayoutManager(new LinearLayoutManager(this));
        AdapterCard adaptercard = new AdapterCard(data);
        rvpahlawan.setAdapter(adaptercard);


    }

    private void tampildatagrid(){
        mview = 1;
        rvpahlawan.setLayoutManager(new GridLayoutManager(this, 2));
        AdapterGrid adapterGrid = new AdapterGrid(data);
        rvpahlawan.setAdapter(adapterGrid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tampilan, menu);


        int nightmode = AppCompatDelegate.getDefaultNightMode();
        if(nightmode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.menu_night).setTitle("Mode Day");
        }
        else{
            menu.findItem(R.id.menu_night).setTitle("Mode Night");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_card:
                setTitle("Mode Card View");
                tampilDataCard();
                break;

            case R.id.menu_grid:
                setTitle("Mode Grid View");
                tampildatagrid();
                break;
            case R.id.menu_night:
                setTitle("Mode Mugen Tsukoyomi");
                int nightmode = AppCompatDelegate.getDefaultNightMode();
                if(nightmode == AppCompatDelegate.MODE_NIGHT_YES){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
                break;
            case R.id.menu_help:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("Tel: 62711376400"));
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_MODE, mview);
        super.onSaveInstanceState(outState);
    }
}



