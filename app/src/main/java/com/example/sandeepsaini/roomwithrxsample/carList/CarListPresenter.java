package com.example.sandeepsaini.roomwithrxsample.carList;

import com.example.sandeepsaini.roomwithrxsample.base.BasePresenter;
import com.example.sandeepsaini.roomwithrxsample.database.DbService;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
public class CarListPresenter extends BasePresenter<CarListContract.ICarListView> implements
        CarListContract.ICarListPresenter {

    private DbService dbService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public CarListPresenter(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public void getCarListFromDB() {

        Disposable disposable = dbService.getCarList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CarModel>>() {
                    @Override
                    public void accept(List<CarModel> carModelList) throws Exception {
                        if (carModelList != null && carModelList.size() > 0) {
                            getView().onGetCarListResponse(carModelList);
                        } else {
                            getView().showNoCarListView();
                        }
                    }
                });
        compositeDisposable.add(disposable);
    }
}
