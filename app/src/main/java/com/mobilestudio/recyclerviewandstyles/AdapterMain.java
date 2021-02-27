package com.mobilestudio.recyclerviewandstyles;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    private List<Contact> sourceData;
    private ItemTap onTap;

    public AdapterMain(List<Contact> sourceData) {
        this.sourceData = sourceData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemMain = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        return new ViewHolder(itemMain);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(sourceData.get(holder.getAdapterPosition()));
        holder.itemView.findViewById(R.id.imgItemMain).setOnClickListener(v -> {
            onTap.OnTapItem(holder.getAdapterPosition(), sourceData.get(holder.getAdapterPosition()));
        });
    }

    @Override
    public int getItemCount() {
        return sourceData.size();
    }


    public void addItem(Contact dataItem) {
        sourceData.add(dataItem);
        notifyItemInserted(sourceData.size());
        Log.i("SourceData", sourceData.toString());
    }

    public void addOnItemTap(ItemTap onTap) {
        this.onTap = onTap;
    }

    public void removeItem(int position) {
        sourceData.remove(position);
        notifyItemRemoved(position);
    }

    interface ItemTap {
        void OnTapItem(int position, Contact data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindView(Contact data) {
            ImageView imgItem = itemView.findViewById(R.id.imgItemMain);
            TextView txtItem = itemView.findViewById(R.id.txtItemMainTitle);

            imgItem.setImageResource(data.getImage());
            txtItem.setText(data.getName());
        }
    }
}
