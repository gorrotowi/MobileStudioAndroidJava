package com.mobilestudio.android_107_database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.mobilestudio.android_107_database.database.ContactDAO;
import com.mobilestudio.android_107_database.database.ContactEntity;
import com.mobilestudio.android_107_database.database.ContactsDatabase;
import com.mobilestudio.android_107_database.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ListContactsViewModel extends AndroidViewModel {

    private final ContactDAO contactDAO;

    //    private MutableLiveData<List<ContactEntity>> contactEntityListMutable = new MutableLiveData<>();
    public LiveData<List<Contact>> contactEntityListData;// = contactEntityListMutable;

    public ListContactsViewModel(@NonNull Application application) {
        super(application);
        ContactsDatabase db = ContactsDatabase.getInstance(getApplication());
        contactDAO = db.getContactDAO();
    }

//    public void getAllContacts() {
//        ContactsDatabase.databaseWriterExecutor.execute(() -> {
//            List<ContactEntity> contactEntityList = contactDAO.getAllContacts();
//            List<Contact> contactList = new ArrayList<>();
//            for (ContactEntity contact : contactEntityList) {
//                contactList.add(new Contact(contact.name, contact.phone, contact.email));
//            }
//            contactEntityListMutable.postValue(contactList);
//        });
//    }

    public void getAllContactsWLiveData() {
        contactEntityListData = Transformations.map(contactDAO.getAllContacts(), contactEntityList -> {
            List<Contact> contactList = new ArrayList<>();
            for (ContactEntity contact : contactEntityList) {
                contactList.add(new Contact(contact.id, contact.name, "", contact.phone, contact.email));
            }
            return contactList;
        });
    }

    public void deleteContact(int id) {
        ContactsDatabase.databaseWriterExecutor.execute(() -> {
            ContactEntity contactEntity = new ContactEntity("", "", "");
            contactEntity.setId(id);
            contactDAO.deleteContact(contactEntity);
        });
    }
}
