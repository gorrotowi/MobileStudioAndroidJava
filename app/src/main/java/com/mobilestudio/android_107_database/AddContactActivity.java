package com.mobilestudio.android_107_database;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mobilestudio.android_107_database.databinding.ActivityAddContactBinding;
import com.mobilestudio.android_107_database.entities.Contact;
import com.mobilestudio.android_107_database.viewmodels.AddContactViewModel;

public class AddContactActivity extends AppCompatActivity {

    private static final String KEY_ID_CONTACT = "KEY_ID";
    int RESULT_CONTACT_UPDATED = 100;
    private ActivityAddContactBinding binding;
    private AddContactViewModel viewModel;


    public static int parseData(Intent intent) {
        Bundle bundleExtras = intent.getExtras();
        return bundleExtras.getInt(KEY_ID_CONTACT, 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(AddContactViewModel.class);

        if (getIntent().getExtras() != null) {
            binding.btnAddUpdateContact.setText(R.string.update_contact);
            int idContact = parseData(getIntent());
            viewModel.getContactByID(idContact);
        }
        setUpViews();
        setUpObersables();
        setUpActions();
    }

    private void setUpObersables() {
        viewModel.contactLiveData().observe(this, this::fillForm);
    }

    private void fillForm(Contact contact) {
        binding.edtxtName.setText(contact.getName());
        binding.edtxtPhone.setText(contact.getPhone());
        binding.edtxtEmail.setText(contact.getEmail());
    }

    private void setUpViews() {
        binding.toolbarAdd.setTitle("");
        setSupportActionBar(binding.toolbarAdd);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpActions() {
        binding.btnAddUpdateContact.setOnClickListener(v -> {
            validateForm();
        });
    }

    private void validateForm() {
        String name = binding.edtxtName.getText().toString();
        String phone = binding.edtxtPhone.getText().toString();
        String email = binding.edtxtEmail.getText().toString();
        if (!name.isEmpty() && !phone.isEmpty()) {
            addOrUpdateContact(name, phone, email);
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

    private void addOrUpdateContact(String name, String phone, String email) {
        if (getIntent().getExtras() != null) {
            int idContact = parseData(getIntent());
            updateContact(idContact, name, phone, email);
        } else {
            addNewContact(name, phone, email);
        }
    }

    private void addNewContact(String name, String phone, String email) {
        Contact contact = new Contact(0, name, "", phone, email);
        viewModel.addNewContact(contact);
        returnContactData();
    }

    private void updateContact(int idContact, String name, String phone, String email) {
        Contact contact = new Contact(idContact, name, "", phone, email);
        viewModel.updateContact(contact);
        Intent intent = new Intent();
        setResult(RESULT_CONTACT_UPDATED, intent);
        finish();
    }

    private void returnContactData() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
        }

        if (item.getItemId() == R.id.actionAddContact) {
            // return data
            returnContactData();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    public static class AddContactContract extends ActivityResultContract<Integer, Boolean> {

        @NonNull
        @Override
        public Intent createIntent(@NonNull Context context, Integer input) {
            Intent intentToBuild = new Intent(context, AddContactActivity.class);
            if (input != null) {
                intentToBuild.putExtra(KEY_ID_CONTACT, input.intValue());
            }
            return intentToBuild;
        }

        @Override
        public Boolean parseResult(int resultCode, @Nullable Intent intent) {
            return resultCode == RESULT_OK;
        }
    }

}
