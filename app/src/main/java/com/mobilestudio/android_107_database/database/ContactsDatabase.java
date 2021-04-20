package com.mobilestudio.android_107_database.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ContactEntity.class}, version = 2, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {

    static final Migration MIGRATION1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE contact ADD COLUMN last_name TEXT default 'null' ");
        }
    };
    public static volatile ContactsDatabase INSTANCE;
    public static ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(4);

    public static ContactsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ContactsDatabase.class) {
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        ContactsDatabase.class,
                        "ContactsDatabase")
                        .addMigrations(MIGRATION1_2)
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract ContactDAO getContactDAO();
}
