package com.example.sandeepsaini.roomwithrxsample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.sandeepsaini.roomwithrxsample.database.Dao.CarDao;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
@Database(entities = {CarModel.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CarDao carDao();

    public static AppDatabase appDatabase;

    public static DbService getDataBase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "myApp")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return DbService.getInstance(appDatabase, context);
    }
}
