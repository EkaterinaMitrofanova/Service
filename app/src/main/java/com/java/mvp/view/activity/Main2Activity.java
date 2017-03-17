package com.java.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.java.mvp.R;
import com.java.mvp.presenter.control.Presenter2;
import com.java.mvp.presenter.interfaces.DataListener;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity implements DataListener{

    Presenter2 presenter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initViews();

    }

    void initViews(){
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String surname = intent.getStringExtra("SURNAME");
        String email = intent.getStringExtra("EMAIL");
        final int position = intent.getIntExtra("POSITION", -1);

        final EditText nameView = (EditText) findViewById(R.id.nameEditText);
        final EditText surnameView = (EditText)findViewById(R.id.surnameEditText);
        final EditText emailView = (EditText)findViewById(R.id.emailEditText);
        ImageView photoView = (ImageView)findViewById(R.id.photo2);
        Button button = (Button)findViewById(R.id.saveButton);

        nameView.setText(name);
        surnameView.setText(surname);
        emailView.setText(email);

        presenter2 = new Presenter2(this);
        Picasso.with(this)
                .load(presenter2.getPhoto(position))
                .fit()
                .into(photoView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter2 presenter2 = new Presenter2(Main2Activity.this);
                presenter2.save(nameView.getText().toString(), surnameView.getText().toString(),
                        emailView.getText().toString(), position);
            }
        });
    }

    void blockField(EditText editText){
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setBackgroundColor(Color.TRANSPARENT);
        editText.setKeyListener(null);
    }

    @Override
    public void changed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
