package com.aqua30.contactfetch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.aqua30.contactfetch.R;
import com.aqua30.contactfetch.pojo.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saurabh(aqua) on 01-11-2016.
 */

public class ContactListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Contact> contacts = new ArrayList<>();

    public ContactListAdapter(List<Contact> contacts){
        this.contacts = contacts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.contact_name.setText(contacts.get(position).getName());
        holder.contact_number.setText(contacts.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return contacts.size() > 0 ? contacts.size() : 0 ;
    }

    public void updateList(List<Contact> updatedList){
        this.contacts = updatedList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contact_name)TextView contact_name;
        @BindView(R.id.contact_number)TextView contact_number;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
