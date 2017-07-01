package com.aqua30.contactfetch.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aqua30.contactfetch.singleton.PermissionClass;
import com.aqua30.contactfetch.R;
import com.aqua30.contactfetch.adapters.ContactListAdapter;
import com.aqua30.contactfetch.adapters.ContactLoader;
import com.aqua30.contactfetch.pojo.Contact;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Saurabh(aqua) on 1-07-2017.
 */

public class ContactFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<Contact>>,
        EasyPermissions.PermissionCallbacks {

    @BindView(R.id.contact_list) RecyclerView contactList;

    private static final int CONTACTS_LOADER_ID = 1;

    private ContactListAdapter adapter;

    public static ContactFragment newInstance(){
        return new ContactFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ContactListAdapter(null);
        contactList.setAdapter(adapter);
        if (EasyPermissions.hasPermissions(getActivity(), PermissionClass.ReadContacts)) {
            getLoaderManager().initLoader(CONTACTS_LOADER_ID,
                    null,
                    this).forceLoad();
        } else {
            readStorageState();
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fg_contacts;
    }

    @Override
    public Loader<List<Contact>> onCreateLoader(int id, Bundle args) {
        return new ContactLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> data) {
        if (data != null) {
            adapter.updateList(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Contact>> loader) {}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {}

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        new AppSettingsDialog.Builder(this, getString(R.string.rationale_ask_again))
                .setTitle(getString(R.string.rationale_ask_again))
                .setPositiveButton(getString(R.string.setting))
                .setNegativeButton(getString(R.string.cancel), null /* click listener */)
                .setRequestCode(PermissionClass.PERMISSION_READ_CONTACTS)
                .build()
                .show();
    }

    @AfterPermissionGranted(PermissionClass.PERMISSION_READ_CONTACTS)
    public void readStorageState() {
        if (EasyPermissions.hasPermissions(getActivity(), PermissionClass.ReadContacts)) {
            getLoaderManager().initLoader(CONTACTS_LOADER_ID,
                    null,
                    this).forceLoad();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale),
                    PermissionClass.PERMISSION_READ_CONTACTS, PermissionClass.ReadContacts);
        }

    }

}