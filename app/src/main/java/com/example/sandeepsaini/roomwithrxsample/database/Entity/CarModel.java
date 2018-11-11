package com.example.sandeepsaini.roomwithrxsample.database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sandeep Saini on 11/6/2018
 */

@Entity(tableName = "car")
public class CarModel implements Parcelable {

    public CarModel() {
    }

    @PrimaryKey
    @ColumnInfo(name = "idCar")
    private long idCar;
    @ColumnInfo(name = "company_name")
    private String companyName;
    @ColumnInfo(name = "car_model")
    private String carModel;
    @ColumnInfo(name = "car_color")
    private String carColor;
    @ColumnInfo(name = "car_description")
    private String carDescription;
    @ColumnInfo(name = "engine_power")
    private String enginePower;
    @ColumnInfo(name = "fuel_type")
    private String fuelType;
    @ColumnInfo(name = "selling_price")
    private String sellingPrice;
    @ColumnInfo(name = "mileage")
    private String mileage;

    protected CarModel(Parcel in) {
        idCar = in.readLong();
        companyName = in.readString();
        carModel = in.readString();
        carColor = in.readString();
        carDescription = in.readString();
        enginePower = in.readString();
        fuelType = in.readString();
        sellingPrice = in.readString();
        mileage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idCar);
        dest.writeString(companyName);
        dest.writeString(carModel);
        dest.writeString(carColor);
        dest.writeString(carDescription);
        dest.writeString(enginePower);
        dest.writeString(fuelType);
        dest.writeString(sellingPrice);
        dest.writeString(mileage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CarModel> CREATOR = new Creator<CarModel>() {
        @Override
        public CarModel createFromParcel(Parcel in) {
            return new CarModel(in);
        }

        @Override
        public CarModel[] newArray(int size) {
            return new CarModel[size];
        }
    };

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public long getIdCar() {
        return idCar;
    }

    public void setIdCar(long idCar) {
        this.idCar = idCar;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
