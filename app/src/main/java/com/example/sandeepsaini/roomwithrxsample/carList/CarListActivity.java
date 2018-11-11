package com.example.sandeepsaini.roomwithrxsample.carList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sandeepsaini.roomwithrxsample.CarDetailActivity;
import com.example.sandeepsaini.roomwithrxsample.R;
import com.example.sandeepsaini.roomwithrxsample.addCar.AddNewCarActiviy;
import com.example.sandeepsaini.roomwithrxsample.database.AppDatabase;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarListActivity extends AppCompatActivity implements CarListContract.ICarListView {

    @BindView(R.id.toolbar_back_button_iv)
    ImageView toolbarBackButtonIV;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTV;

    @BindView(R.id.car_list_rv)
    RecyclerView carListRV;

    @BindView(R.id.no_data_layout)
    RelativeLayout noDataLayout;

    private CarListPresenter carListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_car_list);
        ButterKnife.bind(this);

        setUpPresenter();

        toolbarBackButtonIV.setVisibility(View.GONE);
        toolbarTitleTV.setText(R.string.car_list);
    }

    private void setUpPresenter() {
        carListPresenter = new CarListPresenter(AppDatabase.getDataBase(this));
        carListPresenter.onAttach(this);

        carListPresenter.getCarListFromDB();
    }

    private void showNoDataUI(boolean show) {
        noDataLayout.setVisibility(show ? View.VISIBLE : View.GONE);
        carListRV.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onGetCarListResponse(List<CarModel> carModelList) {
        showNoDataUI(false);
        carListRV.setHasFixedSize(true);
        carListRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        carListRV.setAdapter(new CarListAdapter(this, carModelList, new CarListAdapter.CarListClickListener() {
            @Override
            public void onCarCardClick(CarModel carModel) {
                Intent intent = new Intent(CarListActivity.this, CarDetailActivity.class);
                intent.putExtra("car_details",carModel);
                startActivity(intent);
            }
        }));
    }

    @Override
    public void showNoCarListView() {
        showNoDataUI(true);
    }

//    @OnClick(R.id.toolbar_back_button_iv)
//    void onToolbarBackButtonClick(View view) {
//        finish();
//    }

    @OnClick(R.id.button_add_new_car)
    void onAddNewCarClick(View view) {
        Intent intent = new Intent(this, AddNewCarActiviy.class);
        startActivity(intent);
    }
}
