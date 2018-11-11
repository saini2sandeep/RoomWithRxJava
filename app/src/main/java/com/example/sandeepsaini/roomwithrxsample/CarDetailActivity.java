package com.example.sandeepsaini.roomwithrxsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTV;

    @BindView(R.id.model_tv)
    TextView modelTV;
    @BindView(R.id.color_tv)
    TextView colorTV;
    @BindView(R.id.engine_power_tv)
    TextView enginePowerTV;
    @BindView(R.id.car_description_tv)
    TextView carDescTV;
    @BindView(R.id.fuel_type_tv)
    TextView fuelTypeTV;
    @BindView(R.id.mileage_tv)
    TextView mileageTV;
    @BindView(R.id.selling_price_tv)
    TextView sellingPriceTV;

    private CarModel carModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);
        ButterKnife.bind(this);

        toolbarTitleTV.setText("Car Details");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            carModel = bundle.getParcelable("car_details");
        setUpCarData();
    }

    private void setUpCarData() {
        String enginePower = "";
        if (carModel.getEnginePower().contains("CC"))
            enginePower = "Engine Power: " + carModel.getEnginePower();
        else
            enginePower = "Engine Power: " + carModel.getEnginePower() + " CC";

        String model = carModel.getCompanyName() + ", " + carModel.getCarModel();
        String color = "Color: " + carModel.getCarColor();

        String fuelType = "Fuel: " + carModel.getFuelType();
        String mileage = "Mileage: " + carModel.getMileage() + " Km/l";
        String sellingPrice = "Selling price: " + carModel.getSellingPrice() + " Lack";
        String description = "Description: " + carModel.getCarDescription();

        modelTV.setText(model);
        colorTV.setText(color);
        enginePowerTV.setText(enginePower);
        carDescTV.setText(description);
        fuelTypeTV.setText(fuelType);
        mileageTV.setText(mileage);
        sellingPriceTV.setText(sellingPrice);
    }

    @OnClick(R.id.toolbar_back_button_iv)
    void onToolbarBackButtonClick(View view) {
        finish();
    }
}
