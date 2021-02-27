package com.mobilestudio.recyclerviewandstyles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {

    List<String> sourceData = new ArrayList<>();

    public AdapterMain() {
        sourceData.add("Item 1");
        sourceData.add("Item 2");
        sourceData.add("Item 3");
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
        holder.bindView(sourceData.get(position));
    }

    @Override
    public int getItemCount() {
        return sourceData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindView(String data) {
            TextView txtItem = itemView.findViewById(R.id.txtItemMain);
            txtItem.setText(data);
        }
    }
}
