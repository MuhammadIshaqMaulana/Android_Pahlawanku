package com.kuroyuki.pahlawan;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.ViewHolder> {
    private ArrayList<ModelPahlawan> datapahlawan;

    public AdapterCard(ArrayList<ModelPahlawan> datapahlawan) {
        this.datapahlawan = datapahlawan;
    }

    @NonNull
    @Override
    public AdapterCard.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCard.ViewHolder holder, int position) {
    ModelPahlawan pahlawan = datapahlawan.get(position);
    holder.tvnama.setText(pahlawan.getNama());
    holder.tvtentang.setText(pahlawan.getTentang());
        Glide.with(holder.itemView.getContext())
                .load(pahlawan.getFoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivfoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = pahlawan.getNama();
                String tentang = pahlawan.getTentang();
                String foto = pahlawan.getFoto();

                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("VarNama", nama);
                intent.putExtra("VarTentang", tentang);
                intent.putExtra("VarFoto", foto);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datapahlawan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivfoto;
        TextView tvnama, tvtentang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivfoto = itemView.findViewById(R.id.iv_foto);
            tvnama = itemView.findViewById(R.id.tv_nama);
            tvtentang = itemView.findViewById(R.id.tv_tentang);

        }
    }

}
