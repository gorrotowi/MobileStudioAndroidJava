package com.mobilestudio.android_107_database;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mobilestudio.android_107_database.adapters.ContactAdapter;
import com.mobilestudio.android_107_database.databinding.ActivityMainBinding;
import com.mobilestudio.android_107_database.viewmodels.ListContactsViewModel;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<Integer> getContactLauncher = registerForActivityResult(new AddContactActivity.AddContactContract(), isContactAdded -> {
        if (isContactAdded) {
            Toast.makeText(this, "Contacto nuevo agregado", Toast.LENGTH_SHORT).show();
        }
    });
    private ActivityMainBinding binding;
    private ContactAdapter adapter;

    private ListContactsViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ListContactsViewModel.class);
        adapter = new ContactAdapter();
        viewModel.getAllContactsWLiveData();
        setUpObservables();
        setUpActions();
        setUpViews();

    }

    private void setUpObservables() {
        viewModel.contactEntityListData.observe(this, contactList -> {
            adapter.setDataSource(contactList);
        });
    }

    private void setUpViews() {
        binding.rcContacts.setAdapter(adapter);
    }

    private void setUpActions() {

        adapter.setOnItemClickListener(this::showModifyDialog);

        binding.btnAddContact.setOnClickListener(v -> {
            getContactLauncher.launch(null);
        });
    }

    private void showModifyDialog(int id) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit Contact")
                .setMessage("Do you want to delete or modify this contact?")
                .setPositiveButton("Modify", (dialogInstance, which) -> {
                    getContactLauncher.launch(id);
                    dialogInstance.dismiss();
                })
                .setNegativeButton("Delete", (dialogInstance, which) -> {
                    viewModel.deleteContact(id);
                    dialogInstance.dismiss();
                })
                .setNeutralButton("Cancel", (dialogInstance, wich) -> {
                    dialogInstance.dismiss();
                })
                .create();

        dialog.show();
    }

}
