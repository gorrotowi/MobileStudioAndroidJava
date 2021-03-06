package com.mobilestudio.lifecycleandfragments.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilestudio.lifecycleandfragments.databinding.ItemMailBinding;
import com.mobilestudio.lifecycleandfragments.models.Mail;

import java.util.List;

public class AdapterMail extends RecyclerView.Adapter<AdapterMail.ViewHolder> {

    List<Mail> sourceData;
    AdapterListener listener;

    public AdapterMail(List<Mail> sourceData) {
        this.sourceData = sourceData;
    }

    public void setOnItemClickListener(AdapterListener clickListener) {
        listener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMailBinding binding = ItemMailBinding
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(sourceData.get(position));
        holder.binding.getRoot().setOnClickListener(v -> {
            listener.OnClick(sourceData.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return sourceData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemMailBinding binding;

        public ViewHolder(@NonNull ItemMailBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bindView(Mail mail) {
            binding.txtItemTitle.setText(mail.getTitle());
            binding.txtItemSubject.setText(mail.getSubject());
        }
    }
}
