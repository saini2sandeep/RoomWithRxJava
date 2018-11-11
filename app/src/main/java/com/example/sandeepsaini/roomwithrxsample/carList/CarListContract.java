package com.example.sandeepsaini.roomwithrxsample.carList;

import com.example.sandeepsaini.roomwithrxsample.base.BaseViewContract;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import java.util.List;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
public class CarListContract {


    public interface ICarListView extends BaseViewContract {
        void onGetCarListResponse(List<CarModel> carModelList);

        void showNoCarListView();
    }

    public interface ICarListPresenter {
        void getCarListFromDB();
    }
}
