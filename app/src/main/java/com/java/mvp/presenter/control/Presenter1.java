package com.java.mvp.presenter.control;

import android.content.Context;
import android.content.Intent;

import com.java.mvp.model.ContactsUtil;
import com.java.mvp.presenter.control.services.MyIntentService;
import com.java.mvp.presenter.interfaces.ListScreenListener;

import java.util.HashMap;
import java.util.Map;

public class Presenter1 {

    public static String NAME = "NAME";
    public static String SURNAME = "SURNAME";
    public static String EMAIL = "EMAIL";

    private ListScreenListener listScreenListener;

    public Presenter1(ListScreenListener listScreenListener) {
        this.listScreenListener = listScreenListener;
    }

    public void checked(String name, String surname, String email, int position) {
        listScreenListener.checked(name, surname, email, position);
    }

    public int getSize(){
        ContactsUtil contactsUtil = new ContactsUtil((Context) listScreenListener);
        return contactsUtil.getSize();
    }

    public Map getContact(int position){
        ContactsUtil contactsUtil = new ContactsUtil((Context) listScreenListener);
        Map<String, String> contact = new HashMap<String, String>();
        contact.put(NAME, contactsUtil.getName(position));
        contact.put(SURNAME, contactsUtil.getSurname(position));
        contact.put(EMAIL, contactsUtil.getEmail(position));
        return contact;
    }

    public void startSearch(){
        ContactsUtil contactsUtil = new ContactsUtil((Context) listScreenListener);
        if (contactsUtil.getSize() == 0) {
            Intent intentMyIntentService = new Intent((Context) listScreenListener, MyIntentService.class);
            ((Context) listScreenListener).startService(intentMyIntentService);
        } else {
            listScreenListener.fill();
        }
    }

    public String getPhoto(int position){
        ContactsUtil contactsUtil = new ContactsUtil((Context) listScreenListener);
        return contactsUtil.getPhoto(position);
    }
}
