package com.offcasoftware.shop2.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.offcasoftware.shop2.database.DatabaseImpl;

public class ProductProvider extends ContentProvider {

    private static final String AUTHORITY = "com.offcasoftware.shop2.provider";
    private static final String PRODUCT_URL = "content://" + AUTHORITY + "/products";

    public static final Uri CONTENT_URI = Uri.parse(PRODUCT_URL);

    private static final int PRODUCTS_MATCHER_ID = 1;
    private static final int PRODUCT_MATCHER_ID = 2;
    static final UriMatcher uriMatcher;

    public static final String PRODUCTS = "products";

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "products", PRODUCTS_MATCHER_ID);
        uriMatcher.addURI(AUTHORITY, "products/#", PRODUCT_MATCHER_ID);
    }

    private DatabaseImpl mDatabase;

    @Override
    public boolean onCreate() {
        mDatabase = new DatabaseImpl(getContext().getApplicationContext());
        final boolean state = mDatabase.getWritableDatabase() != null;
        return state;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case PRODUCTS_MATCHER_ID:
                return mDatabase.getReadableDatabase().query(PRODUCTS, null, null, null, null, null, null);
            case PRODUCT_MATCHER_ID:
                final String id = uri.getLastPathSegment();
                return mDatabase.getReadableDatabase().query("products", null,
                        "id = ?", new String[]{id}, null, null, null);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
