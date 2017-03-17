package com.java.mvp.presenter.interfaces;

import com.java.mvp.model.users.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Link {

    @GET("/api/?page=1&&seed=abc&results=10&inc=name,email,picture&nat=us")
    Call<UserList> getUsers();
}
