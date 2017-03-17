package com.java.mvp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.java.mvp.R;
import com.java.mvp.presenter.control.Presenter1;
import com.java.mvp.presenter.interfaces.ListScreenListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    private ListScreenListener listScreenListener;

    public RecyclerAdapter(ListScreenListener listScreenListener) {
        this.listScreenListener = listScreenListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        Presenter1 presenter1 = new Presenter1(listScreenListener);
        Map contact = presenter1.getContact(position);
        String name = (String) contact.get(Presenter1.NAME);
        String surname = (String) contact.get(Presenter1.SURNAME);
        String email = (String) contact.get(Presenter1.EMAIL);
        if (name != null){
            holder.nameView.setText(name);
        }
        if (surname != null){
            holder.surnameView.setText(surname);
        }
        if (email!= null){
            holder.emailView.setText(email);
        }

        Picasso.with(holder.view.getContext())
                .load(presenter1.getPhoto(position))
                .fit()
                .into(holder.imageView);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter1 presenter1 = new Presenter1(listScreenListener);
                presenter1.checked(holder.nameView.getText().toString(), holder.surnameView.getText().toString(),
                        holder.emailView.getText().toString(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Presenter1 presenter1 = new Presenter1(listScreenListener);
        return presenter1.getSize();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, surnameView, emailView;
        ImageView imageView;
        View view;

        RecyclerViewHolder(View view) {
            super(view);
            this.view = view;
            nameView = (TextView)view.findViewById(R.id.nameTextView);
            surnameView = (TextView)view.findViewById(R.id.surnameTextView);
            emailView = (TextView)view.findViewById(R.id.emailTextView);
            imageView = (ImageView)view.findViewById(R.id.photo1);
        }
    }
}
