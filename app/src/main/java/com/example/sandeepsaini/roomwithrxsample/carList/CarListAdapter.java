package com.example.sandeepsaini.roomwithrxsample.carList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sandeepsaini.roomwithrxsample.R;
import com.example.sandeepsaini.roomwithrxsample.database.Entity.CarModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarListViewHolder> {

    private Context context;
    private List<CarModel> carModelList;
    private CarListClickListener carListClickListener;

    public CarListAdapter(Context context, List<CarModel> carModelList, CarListClickListener carListClickListener) {
        this.context = context;
        this.carModelList = carModelList;
        this.carListClickListener = carListClickListener;
    }

    @NonNull
    @Override
    public CarListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_car,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarListViewHolder holder, int position) {
        holder.setViewData(carModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return carModelList == null ? 0 : carModelList.size();
    }

    public class CarListViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.more_tv)
        TextView moreTV;

        public CarListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setViewData(final CarModel carModel) {
            String enginePower = "";
            if (carModel.getEnginePower().contains("CC"))
                enginePower = "Engine Power: " + carModel.getEnginePower();
            else
                enginePower = "Engine Power: " + carModel.getEnginePower() + " CC";

            String model = carModel.getCompanyName() + ", " + carModel.getCarModel();
            String color = "Color: " + carModel.getCarColor();
            String fuelType = "Fuel: " + carModel.getFuelType();

            modelTV.setText(model);
            colorTV.setText(color);
            enginePowerTV.setText(enginePower);
            carDescTV.setText(carModel.getCarDescription());
            fuelTypeTV.setText(fuelType);

              /*
                Checking the Car description text is exceding 4 lines or not
                    if yes -> make 'More' text visible
                    no -> make 'More' text invisible
            */
            carDescTV.post(new Runnable() {
                @Override
                public void run() {
                    int lineCount = carDescTV.getLineCount();
                    if (lineCount >= 4)
                        moreTV.setVisibility(View.VISIBLE);
                    else
                        moreTV.setVisibility(View.GONE);
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carListClickListener.onCarCardClick(carModel);
                }
            });
        }
    }


    public interface CarListClickListener {
        void onCarCardClick(CarModel carModel);
    }
}
