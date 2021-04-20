package com.mobilestudio.android_107_database.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDAO {

    @Query("SELECT * FROM contact")
    LiveData<List<ContactEntity>> getAllContacts();

    @Query("SELECT * FROM contact WHERE id = :ID")
    ContactEntity getContactByID(int ID);

    @Transaction
    @Query("SELECT * FROM contact WHERE name LIKE :name AND email LIKE :name")
    List<ContactEntity> getContactsByName(String name);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long addNewContact(ContactEntity newContact);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateContact(ContactEntity newContact);

    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateContactList(List<ContactEntity> contactEntities);
    void updateContactList(ContactEntity... contactEntities);

    @Delete
    void deleteContact(ContactEntity contact);

    @Delete
    void deleteContactList(ContactEntity... contact);

    @Query("DELETE FROM contact")
    void deleteAllContacts();

}
