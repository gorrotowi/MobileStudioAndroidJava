package com.mobilestudio.android_107_database.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilestudio.android_107_database.databinding.ItemContactBinding;
import com.mobilestudio.android_107_database.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> dataSource = new ArrayList<>();
    private OnItemListener itemListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactBinding binding = ItemContactBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(dataSource.get(position));
        holder.binding.getRoot().setOnClickListener(v -> {
            int idContact = dataSource.get(position).getID();
            itemListener.OnItemClick(idContact);
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(List<Contact> contactList) {
        dataSource = contactList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemListener listener) {
        itemListener = listener;
    }

    public interface OnItemListener {
        void OnItemClick(int id);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemContactBinding binding;

        public ViewHolder(@NonNull ItemContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Contact contact) {
            binding.txtItemContactName.setText(contact.getName());
            binding.txtItemContactPhone.setText(contact.getPhone());
        }
    }
}
