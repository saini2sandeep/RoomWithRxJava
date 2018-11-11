package com.example.sandeepsaini.roomwithrxsample.database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
@Dao
public interface CarDao {
    @Query(("SELECT * FROM car"))
    Flowable<List<CarModel>> getCarList();

    @Insert
    void insertCar(CarModel carModel);

    @Query("DELETE FROM car")
    void flushTable();
}
