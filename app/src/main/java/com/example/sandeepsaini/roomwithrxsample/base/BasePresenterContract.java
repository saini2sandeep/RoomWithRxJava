package com.example.sandeepsaini.roomwithrxsample.base;

/**
 * Created by Sandeep Saini on 11/6/2018
 */
public interface BasePresenterContract <V extends BaseViewContract> {

    void onAttach(V view);

    void onDetach();

    boolean isViewAttached();

    V getView();

    void setUserAsLoggedOut();
}
