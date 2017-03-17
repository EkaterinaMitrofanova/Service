package com.java.mvp.presenter.control;

import android.content.Context;
import android.widget.Toast;

import com.java.mvp.model.ContactsUtil;
import com.java.mvp.presenter.interfaces.DataListener;

public class Presenter2 {

    private DataListener dataListener;

    public Presenter2(DataListener dataListener) {
        this.dataListener = dataListener;
    }

    public void save(String name, String surname, String email, int position){
        if (position != -1) {
            if (name.matches("(\\W*\\w+\\W*)+")) {
                ContactsUtil contactsUtil = new ContactsUtil((Context) dataListener);
                contactsUtil.save(name, surname, email, position);
                Toast.makeText((Context) dataListener, "Saved", Toast.LENGTH_SHORT).show();
                dataListener.changed();
            } else {
                Toast.makeText((Context) dataListener, "Enter name", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPhoto(int position){
        ContactsUtil contactsUtil = new ContactsUtil((Context)dataListener);
        return contactsUtil.getPhoto(position);
    }
}
