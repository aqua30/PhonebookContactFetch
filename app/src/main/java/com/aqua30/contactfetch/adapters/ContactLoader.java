package com.aqua30.contactfetch.adapters;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.aqua30.contactfetch.pojo.Contact;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContactLoader extends AsyncTaskLoader<List<Contact>> {

	public ContactLoader(Context context) {
		super(context);
	}

	@Override
	public List<Contact> loadInBackground() {
		/* contact list to be returned */
		List<Contact> contacts = new ArrayList<>();

		/* Map which removes the duplicate values */
		Map<String, Contact> map = new LinkedHashMap<>();

		/* creating the projection which indicates what all data we want from our contacts database */
		String[] projection = { ContactsContract.CommonDataKinds.Phone._ID,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER,
				ContactsContract.CommonDataKinds.Email.ADDRESS};

		Cursor contact_cursor = null;
		contact_cursor = getContext().getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				projection, null, null,
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

		/* adding all contacts */
		try {
			contact_cursor.moveToFirst();
			do {
				Contact contact = new Contact();
				String number = contact_cursor.getString(2).replace(" ","");
				contact.setName(contact_cursor.getString(1));
				contact.setNumber(number);
				contact.setEmail(contact_cursor.getString(3));
				map.put(contact.getNumber(), contact);
			} while (contact_cursor.moveToNext());

			contact_cursor.close();
			contacts.addAll(map.values());

			return contacts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
