package com.aqua30.contactfetch.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aqua30.contactfetch.R;
import com.aqua30.contactfetch.fragment.ContactFragment;

import butterknife.BindView;

/**
 * Created by Saurabh(aqua) on 21-11-2016.
 */

public class ContactActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_contacts, ContactFragment.newInstance(), "ContactFragment")
                .commit();
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.ac_contact_selection;
    }

}
