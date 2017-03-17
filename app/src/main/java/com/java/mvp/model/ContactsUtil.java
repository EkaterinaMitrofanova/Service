package com.java.mvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.java.mvp.model.users.Result;

import java.util.List;
import java.util.Map;

public class ContactsUtil {

    private Context context;

    private static final String APP_PREFERENCES_NAME = "Name";
    private static final String APP_PREFERENCES_SURNAME = "Surname";
    private static final String APP_PREFERENCES_EMAIL = "Email";
    private static final String APP_PREFERENCES_PHOTO = "Photo";

    public ContactsUtil(Context context) {
        this.context = context;
    }

    public String getName(int position){
        SharedPreferences contacts = context.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        Map<String, ?> map = contacts.getAll();
        if (map.containsKey(APP_PREFERENCES_NAME+position)) {
            return (String) map.get(APP_PREFERENCES_NAME + position);
        } else {
            return null;
        }
    }

    public String getSurname(int position){
        SharedPreferences contacts = context.getSharedPreferences(APP_PREFERENCES_SURNAME, Context.MODE_PRIVATE);
        Map<String, ?> map = contacts.getAll();
        if (map.containsKey(APP_PREFERENCES_SURNAME+position)) {
            return (String) map.get(APP_PREFERENCES_SURNAME + position);
        } else {
            return null;
        }
    }

    public String getEmail(int position){
        SharedPreferences contacts = context.getSharedPreferences(APP_PREFERENCES_EMAIL, Context.MODE_PRIVATE);
        Map<String, ?> map = contacts.getAll();
        if (map.containsKey(APP_PREFERENCES_EMAIL+position)) {
            return (String) map.get(APP_PREFERENCES_EMAIL + position);
        } else {
            return null;
        }
    }

    public String getPhoto(int position){
        SharedPreferences contacts = context.getSharedPreferences(APP_PREFERENCES_PHOTO, Context.MODE_PRIVATE);
        Map<String, ?> map = contacts.getAll();
        if (map.containsKey(APP_PREFERENCES_PHOTO+position)) {
            return (String) map.get(APP_PREFERENCES_PHOTO + position);
        } else {
            return null;
        }
    }

    public void fill(List<Result> resultList){
        SharedPreferences names = context.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        if (names.getAll().size() == 0) {
            SharedPreferences surnames = context.getSharedPreferences(APP_PREFERENCES_SURNAME, Context.MODE_PRIVATE);
            SharedPreferences emails = context.getSharedPreferences(APP_PREFERENCES_EMAIL, Context.MODE_PRIVATE);
            SharedPreferences photos = context.getSharedPreferences(APP_PREFERENCES_PHOTO, Context.MODE_PRIVATE);
            SharedPreferences.Editor editorName = names.edit();
            SharedPreferences.Editor editorSurname = surnames.edit();
            SharedPreferences.Editor editorEmail = emails.edit();
            SharedPreferences.Editor editorPhoto = photos.edit();
            for (int i = 0; i < resultList.size(); i++) {
                String name, surname, email, photo;
                name = resultList.get(i).getName().getFirst();
                surname = resultList.get(i).getName().getLast();
                email = resultList.get(i).getEmail();
                photo = resultList.get(i).getPicture().getMedium();

                editorName.putString(APP_PREFERENCES_NAME + i, name);
                editorSurname.putString(APP_PREFERENCES_SURNAME + i, surname);
                editorEmail.putString(APP_PREFERENCES_EMAIL + i, email);
                editorPhoto.putString(APP_PREFERENCES_PHOTO + i, photo);

            }
            editorName.apply();
            editorSurname.apply();
            editorEmail.apply();
            editorPhoto.apply();
        }
    }

    public int getSize(){
        Map map = context.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE).getAll();
        return map.size();
    }

    public void save(String name, String surname, String email, int position){
        SharedPreferences names = context.getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences surnames = context.getSharedPreferences(APP_PREFERENCES_SURNAME, Context.MODE_PRIVATE);
        SharedPreferences emails = context.getSharedPreferences(APP_PREFERENCES_EMAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorName = names.edit();
        SharedPreferences.Editor editorSurname = surnames.edit();
        SharedPreferences.Editor editorEmail = emails.edit();
        editorName.putString(APP_PREFERENCES_NAME+position, name);
        editorSurname.putString(APP_PREFERENCES_SURNAME+position, surname);
        editorEmail.putString(APP_PREFERENCES_EMAIL+position, email);
        editorName.apply();
        editorSurname.apply();
        editorEmail.apply();
    }
}
