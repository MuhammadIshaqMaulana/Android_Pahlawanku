package com.kuroyuki.pahlawan;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;

        import androidx.annotation.NonNull;
        import androidx.annotation.RequiresOptIn;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.request.RequestOptions;

        import java.util.ArrayList;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ViewHolder> {
    private ArrayList<ModelPahlawan> datapahlawan;

    public AdapterGrid(ArrayList<ModelPahlawan> datapahlawan) {
        this.datapahlawan = datapahlawan;
    }

    @NonNull
    @Override
    public AdapterGrid.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGrid.ViewHolder holder, int position) {
        ModelPahlawan pahlawan = datapahlawan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(pahlawan.getFoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivgrid);
    }

    @Override
    public int getItemCount() {
        return datapahlawan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivgrid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivgrid = itemView.findViewById(R.id.iv_grid);
        }
    }
}