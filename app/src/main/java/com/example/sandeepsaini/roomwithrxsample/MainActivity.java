package com.example.sandeepsaini.roomwithrxsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandeepsaini.roomwithrxsample.addCar.AddNewCarActiviy;
import com.example.sandeepsaini.roomwithrxsample.carList.CarListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_back_button_iv)
    ImageView toolbarBackButtonIV;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbarBackButtonIV.setVisibility(View.GONE);
        toolbarTitleTV.setText("Room with Rx");

        Intent intent = new Intent(this,CarListActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.button_add_new_car)
    void addNewCarClick(View view){
        Intent intent = new Intent(this,AddNewCarActiviy.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_show_car_list)
    void showCarListClick(View view){
        Intent intent = new Intent(this,CarListActivity.class);
        startActivity(intent);
    }
}
