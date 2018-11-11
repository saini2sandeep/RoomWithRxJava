package com.example.sandeepsaini.roomwithrxsample.addCar;

import com.example.sandeepsaini.roomwithrxsample.base.BaseViewContract;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
public class AddNewCarContract {


    public interface IAddNewCarView extends BaseViewContract {
        void addCarToDataBaseResponse();
    }


    public interface IAddNewCarPresenter {
        void addCarToDataBase(CarModel carModel);
    }
}
