package com.java.mvp.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.java.mvp.R;
import com.java.mvp.presenter.control.services.MyIntentService;
import com.java.mvp.presenter.control.Presenter1;
import com.java.mvp.presenter.interfaces.ListScreenListener;
import com.java.mvp.view.adapter.RecyclerAdapter;

public class MainActivity extends AppCompatActivity implements ListScreenListener {

    MyReciever mMyBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyBroadcastReceiver = new MyReciever();
        IntentFilter intentFilter = new IntentFilter(
                MyIntentService.ACTION_MYINTENTSERVICE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(mMyBroadcastReceiver, intentFilter);

        Presenter1 presenter1 = new Presenter1(this);
        presenter1.startSearch();
    }

    @Override
    public void fill(){
        initViews();
    }

    void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.Adapter adapter = new RecyclerAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void checked(String name, String surname, String email, int position) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("NAME", name);
        intent.putExtra("SURNAME", surname);
        intent.putExtra("EMAIL", email);
        intent.putExtra("POSITION", position);
        startActivity(intent);
    }

    public class MyReciever extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent
                    .getStringExtra(MyIntentService.EXTRA_KEY_OUT);
            if (result.equals("ok")) {
                fill();
            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyBroadcastReceiver);
    }
}
