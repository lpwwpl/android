package cn.com.farsight.cr.contact;

import android.app.ListActivity;

import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;


public class Main extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Uri uri = Uri.parse("content://com.android.contacts/contacts");
		Cursor c = getContentResolver().query(
				uri,
				null, null,// query(Phones.CONTENT_URI,
				null, null);
		startManagingCursor(c);
		ListAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2, c, new String[] {
						ContactsContract.Contacts._ID,
						ContactsContract.Contacts.DISPLAY_NAME						 
						 }, new int[] {	android.R.id.text1, android.R.id.text2 });
		setListAdapter(adapter);
	}

}