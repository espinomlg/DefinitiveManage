package com.jsw.MngProductDatabase.Fragments;


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

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsw.MngProductDatabase.Model.Pharmacy;
import com.jsw.MngProductDatabase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagePharmacy extends Fragment {

    private IMngPharmacy mCallback;
    private TextInputLayout mTilCif, mTilAddres, mTilPhone;

    private FloatingActionButton mFab;
    //private IPharmacyListener mCallback;

    public ManagePharmacy() {
        // Required empty public constructor
    }

    public static ManagePharmacy getInstance() {
        return new ManagePharmacy();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (IMngPharmacy) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_manage_pharmacy, container, false);
        mFab = (FloatingActionButton) rootview.findViewById(R.id.fab_save_pharmacy);
        mTilCif = (TextInputLayout) rootview.findViewById(R.id.til_cif);
        mTilAddres = (TextInputLayout) rootview.findViewById(R.id.til_address);
        mTilPhone = (TextInputLayout) rootview.findViewById(R.id.til_phone);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pharmacy p = new Pharmacy(0,
                        mTilCif.getEditText().getText().toString(),
                        mTilAddres.getEditText().getText().toString(),
                        mTilPhone.getEditText().getText().toString());
                mCallback.addPharmacy(p);
            }
        });
    }

    public interface IMngPharmacy {
        void addPharmacy(Pharmacy pharmacy);
    }

}
