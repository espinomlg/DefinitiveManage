package com.jsw.MngProductDatabase.Adapter;

/*
 * Copyright (c) 2017 José Luis del Pino Gallardo.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *  jose.gallardo994@gmail.com
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.jsw.MngProductDatabase.Model.Pharmacy;
import com.jsw.MngProductDatabase.R;

/**
 * Created by usuario on 30/01/17.
 */

public class PharmacyAdapter extends CursorAdapter {

    private int layout;


    public PharmacyAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(context);
        View rootView = lf.inflate(R.layout.item_pharmacy, parent, false);
        PharmacyHolder ph = new PharmacyHolder();
        ph.tvAddress = (TextView)rootView.findViewById(R.id.tv_phar_address);
        ph.tvCif = (TextView)rootView.findViewById(R.id.tv_phar_cif);
        ph.tvPhone = (TextView)rootView.findViewById(R.id.tv_phar_phone);
        rootView.setTag(ph);
        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        PharmacyHolder viewHolder = (PharmacyHolder)view.getTag();
        viewHolder.tvCif.setText(cursor.getString(1));
        viewHolder.tvAddress.setText(cursor.getString(2));
        viewHolder.tvPhone.setText(cursor.getString(3));
    }

    @Override
    public Object getItem(int position) {
        getCursor().moveToPosition(position);
        Pharmacy p = new Pharmacy(
                getCursor().getInt(0),
                getCursor().getString(1),
                getCursor().getString(2),
                getCursor().getString(3)
        );

        return p;
    }

    class PharmacyHolder{
        TextView tvCif;
        TextView tvAddress;
        TextView tvPhone;
    }
}
