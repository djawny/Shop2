package com.offcasoftware.shop2.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.offcasoftware.shop2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {

    private static final int READ_CONTACTS_REQUEST = 1;

    @BindView(R.id.contacts_counter)
    TextView mContactsCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACTS_REQUEST);
        } else {
            loadContacts();
        }
    }

    private void loadContacts() {
        Uri contentUri = ContactsContract.Contacts.CONTENT_URI;
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor != null) {
            mContactsCounter.setText(String.valueOf(cursor.getCount()));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
