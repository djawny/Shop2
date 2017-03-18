package com.offcasoftware.shop2.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.adapter.ContactAdapter;
import com.offcasoftware.shop2.model.Contact;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {

    private static final int READ_CONTACTS_REQUEST = 1;

    @BindView(R.id.contact_recycle_view)
    RecyclerView mContactRecycleView;

    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        mAdapter = new ContactAdapter(this);
        mContactRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mContactRecycleView.setAdapter(mAdapter);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                Toast.makeText(this, "Błagam kliknij", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_REQUEST);
        } else {
            loadContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_CONTACTS_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadContacts();
                } else {
                    Toast.makeText(this, "No permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void loadContacts() {
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return;
        }

        String columnName = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
                ContactsContract.Contacts.DISPLAY_NAME;

        List<Contact> items = new ArrayList<>();

        cursor.moveToFirst();
        do {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex(columnName));
            Contact contact = new Contact(id, name);
            items.add(contact);
        } while (cursor.moveToNext());
        cursor.close();

        mAdapter.swapData(items);
    }
}
