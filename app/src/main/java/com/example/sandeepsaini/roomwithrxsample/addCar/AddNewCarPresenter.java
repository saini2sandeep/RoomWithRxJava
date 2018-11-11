package com.example.sandeepsaini.roomwithrxsample.addCar;

import com.example.sandeepsaini.roomwithrxsample.base.BasePresenter;
import com.example.sandeepsaini.roomwithrxsample.database.DbService;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
public class AddNewCarPresenter extends BasePresenter<AddNewCarContract.IAddNewCarView>
        implements AddNewCarContract.IAddNewCarPresenter {

    private DbService dbService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AddNewCarPresenter(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public void addCarToDataBase(CarModel carModel) {
        dbService.insertCar(carModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Void aVoid) {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getView().addCarToDataBaseResponse();
                    }
                });

    }
}
