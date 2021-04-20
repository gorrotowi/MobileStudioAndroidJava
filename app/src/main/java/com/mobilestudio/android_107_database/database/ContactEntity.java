package com.mobilestudio.android_107_database.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contact")
public class ContactEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "telephone")
    public String phone;

    @ColumnInfo
    public String email;

    public ContactEntity(String name, String lastName, String phone, String email) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    @Ignore
    public ContactEntity(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
