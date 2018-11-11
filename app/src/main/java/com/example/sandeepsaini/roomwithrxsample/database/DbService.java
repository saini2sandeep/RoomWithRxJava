package com.example.sandeepsaini.roomwithrxsample.database;

import android.content.Context;

import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
public class DbService {

    private AppDatabase appDatabase;
    private Context context;
    private CompositeDisposable compositeDisposable;

    public DbService(AppDatabase appDatabase, Context context) {
        compositeDisposable = new CompositeDisposable();
        this.context = context;
        this.appDatabase = appDatabase;
    }

    public static DbService getInstance(AppDatabase appDatabase, Context context) {
        return new DbService(appDatabase, context);
    }

    //Car
    public Observable<Void> insertCar(final CarModel carModel) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.carDao().insertCar(carModel);
            }
        }).toObservable();

    }

    public Observable<List<CarModel>> getCarList() {
        return Observable.fromPublisher(appDatabase.carDao().getCarList());
    }
}
