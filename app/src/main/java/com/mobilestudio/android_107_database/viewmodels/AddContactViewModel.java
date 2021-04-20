package com.mobilestudio.android_107_database.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mobilestudio.android_107_database.database.ContactDAO;
import com.mobilestudio.android_107_database.database.ContactEntity;
import com.mobilestudio.android_107_database.database.ContactsDatabase;
import com.mobilestudio.android_107_database.entities.Contact;

public class AddContactViewModel extends AndroidViewModel {

    private final ContactDAO contactsDAO;

    private MutableLiveData<Contact> mutableContact = new MutableLiveData<>();

    public AddContactViewModel(@NonNull Application application) {
        super(application);
        ContactsDatabase db = ContactsDatabase.getInstance(getApplication());
        contactsDAO = db.getContactDAO();
    }

    public LiveData<Contact> contactLiveData() {
        return mutableContact;
    }

    public void addNewContact(Contact contact) {
        ContactEntity contactEntity = new ContactEntity(contact.getName(), contact.getPhone(), contact.getEmail());
        ContactsDatabase.databaseWriterExecutor.execute(() -> {
            long rowID = contactsDAO.addNewContact(contactEntity);
            Log.e("ROWID ADDED", "---> " + rowID);
        });
    }

    public void getContactByID(int idContact) {
        ContactsDatabase.databaseWriterExecutor.execute(() -> {
            ContactEntity contactEntity = contactsDAO.getContactByID(idContact);
            Contact contact = new Contact(contactEntity.id, contactEntity.name, "", contactEntity.phone, contactEntity.email);
            mutableContact.postValue(contact);
        });
    }

    public void updateContact(Contact contact) {
        ContactsDatabase.databaseWriterExecutor.execute(() -> {
            ContactEntity contactEntity = new ContactEntity(contact.getName(), contact.getPhone(), contact.getEmail());
            contactEntity.setId(contact.getID());
            contactsDAO.updateContact(contactEntity);
        });
    }
}
