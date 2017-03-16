package com.offcasoftware.shop2;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.offcasoftware.shop2.database.Database;
import com.offcasoftware.shop2.database.DatabaseOrmImpl;

import java.sql.SQLException;

public class AndroidApplication extends Application {

    private static Database mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        //mDatabase = new DatabaseImpl(this);
        mDatabase = OpenHelperManager.getHelper(
                this, DatabaseOrmImpl.class);
        //((DatabaseImpl) mDatabase).getWritableDatabase();
        try {
            ((DatabaseOrmImpl) mDatabase)
                    .getConnectionSource()
                    .getReadWriteConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getDatabase() {
        return mDatabase;
    }
}
