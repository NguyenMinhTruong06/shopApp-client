package com.example.showappclient.localdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.showappclient.localdb.dao.CartDao;
import com.example.showappclient.localdb.entity.Cart;

@Database(entities = {Cart.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

    private static AppDatabase INSTANCE;
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE cart ADD COLUMN productId INTEGER NOT NULL DEFAULT 0");
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "cart")
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return INSTANCE;
    }
}