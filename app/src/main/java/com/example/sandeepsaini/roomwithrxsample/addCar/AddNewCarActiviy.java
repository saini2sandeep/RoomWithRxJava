package com.example.sandeepsaini.roomwithrxsample.addCar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sandeepsaini.roomwithrxsample.R;
import com.example.sandeepsaini.roomwithrxsample.database.AppDatabase;
import com.example.sandeepsaini.roomwithrxsample.database.DbService;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewCarActiviy extends AppCompatActivity implements AddNewCarContract.IAddNewCarView {

    @BindView(R.id.toolbar_back_button_iv)
    ImageView toolbarBackButtonIV;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTV;

    @BindView(R.id.company_name_et)
    EditText companyNameET;
    @BindView(R.id.model_et)
    EditText modelET;
    @BindView(R.id.color_et)
    EditText colorET;
    @BindView(R.id.description_et)
    EditText descriptionET;
    @BindView(R.id.engine_power_et)
    EditText enginePowerET;
    @BindView(R.id.mielage_et)
    EditText mielageET;
    @BindView(R.id.selling_price_et)
    EditText sellingPriceET;
    @BindView(R.id.fuel_type_spinner)
    Spinner fuelTypeSpinner;

    @BindView(R.id.button_add_car)
    Button addNewCarButton;

    private AddNewCarPresenter addNewCarPresenter;
    private DbService dbService;
    private String fuelType = "";

    private static final String[] fuelTypeList = {"-Select-", "Diesel", "Petrol"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_car_activiy);
        ButterKnife.bind(this);
        toolbarTitleTV.setText(R.string.add_new_car);
        setUpPresenter();
        setUpSpinner();
    }

    private void setUpSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewCarActiviy.this,
                R.layout.layut_spinner_text, fuelTypeList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fuelTypeSpinner.setAdapter(adapter);
        fuelTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1) {
                    fuelType = fuelTypeList[position];
                    Log.d("Sandy", "fuel type : " + fuelType);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpPresenter() {
        addNewCarPresenter = new AddNewCarPresenter(AppDatabase.getDataBase(this));
        addNewCarPresenter.onAttach(this);
    }

    @OnClick(R.id.button_add_car)
    void addCarClick(View view) {
        addCarDetails();
    }

    @OnClick(R.id.toolbar_back_button_iv)
    void onToolbarBackButtonClick(View view) {
        finish();
    }

    private void addCarDetails() {
        String companyName = companyNameET.getText().toString().trim();
        String model = modelET.getText().toString().trim();
        String color = colorET.getText().toString().trim();
        String description = descriptionET.getText().toString().trim();
        String enginePower = enginePowerET.getText().toString().trim();
        String mileage = mielageET.getText().toString().trim();
        String sellingPrice = sellingPriceET.getText().toString().trim();

        if (TextUtils.isEmpty(companyName)) {
            showToast("Company name can't be empty.");
            return;
        }
        if (TextUtils.isEmpty(model)) {
            showToast("Car model can't be empty.");
            return;
        }
        if (TextUtils.isEmpty(color)) {
            showToast("Car model can't be empty.");
            return;
        }
        if (TextUtils.isEmpty(description)) {
            showToast("Car description can't be empty.");
            return;
        }
        if (TextUtils.isEmpty(enginePower)) {
            showToast("Engine power can't be empty.");
            return;
        }
        if (TextUtils.isEmpty(fuelType)) {
            showToast("Fuel type can't be empty.");
            return;
        }
        if (TextUtils.isEmpty(mileage)) {
            showToast("Mileage can't be empty.");
            return;
        }
        if (TextUtils.isEmpty(sellingPrice)) {
            showToast("Selling price can't be empty.");
            return;
        }

        CarModel carModel = new CarModel();

        carModel.setIdCar(System.currentTimeMillis());
        carModel.setCompanyName(companyName);
        carModel.setCarModel(model);
        carModel.setCarColor(color);
        carModel.setCarDescription(description);
        carModel.setEnginePower(enginePower);
        carModel.setFuelType(fuelType);
        carModel.setMileage(mileage);
        carModel.setSellingPrice(sellingPrice);

        addNewCarPresenter.addCarToDataBase(carModel);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addCarToDataBaseResponse() {
        showToast("Car added to list. ");
        finish();
    }

    private void makeAddNewButtonEnable(boolean enable) {
        addNewCarButton.setEnabled(enable);
        addNewCarButton.setClickable(enable);
    }
}
