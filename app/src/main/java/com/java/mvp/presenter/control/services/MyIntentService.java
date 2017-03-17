package com.java.mvp.presenter.control.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.java.mvp.model.ContactsUtil;
import com.java.mvp.model.users.Result;
import com.java.mvp.model.users.UserList;
import com.java.mvp.presenter.interfaces.Link;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyIntentService extends IntentService{

    private static final String  BASE_URL = "https://randomuser.me";
    private Retrofit client = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static final String ACTION_MYINTENTSERVICE = "com.java.intentservice.RESPONSE";
    public static final String EXTRA_KEY_OUT = "RES";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        List<Result> userList = null;
        Link service = client.create(Link.class);
        Call<UserList> call = service.getUsers();
        try {
            Response<UserList> response = call.execute();

            userList = response.body().getResults();

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (userList != null) {
            Log.i("LENGHT", String.valueOf(userList.size()));
        } else {
            Log.i("LENGHT", "Null");
        }

        ContactsUtil contactsUtil = new ContactsUtil(this);
        contactsUtil.fill(userList);

        Intent responseIntent = new Intent();
        responseIntent.setAction(ACTION_MYINTENTSERVICE);
        responseIntent.addCategory(Intent.CATEGORY_DEFAULT);
        if (userList != null) {
            responseIntent.putExtra(EXTRA_KEY_OUT, "ok");
        } else {
            responseIntent.putExtra(EXTRA_KEY_OUT, "error");
        }
        sendBroadcast(responseIntent);

    }
}
