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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsw.MngProductDatabase.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagePharmacy extends Fragment {

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
        //mCallback = (IPharmacyListener)activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_manage_pharmacy, container, false);
        mFab = (FloatingActionButton) rootview.findViewById(R.id.fab_add_pharmacy);
        return rootview;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.addPharmacy();
            }
        });*/

    }

}
