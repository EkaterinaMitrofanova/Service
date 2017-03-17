package com.java.mvp.presenter.interfaces;


import android.content.Context;

public interface ListScreenListener {

    void checked(String name, String surname, String email, int position);

    void fill();
}

