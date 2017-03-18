package com.offcasoftware.shop2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.model.Contact;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends BaseAdapter<Contact> {

    public ContactAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, Contact contact, int position) {
        ((ProductViewHolder) holder).bind(contact);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductViewHolder(getLayoutInflater().inflate(R.layout.item_contact, parent, false));
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contact_name)
        TextView mContactName;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Contact contact) {
            mContactName.setText(contact.getmName());
        }
    }
}
