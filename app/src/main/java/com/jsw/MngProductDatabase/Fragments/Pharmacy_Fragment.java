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
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsw.MngProductDatabase.Adapter.PharmacyAdapter;
import com.jsw.MngProductDatabase.Adapter.ProductAdapter;
import com.jsw.MngProductDatabase.Cursors.PharmacyCursorLoader;
import com.jsw.MngProductDatabase.Model.Pharmacy;
import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.Presenter.PharmacyPresenter;
import com.jsw.MngProductDatabase.Presenter.ProductPresenter;
import com.jsw.MngProductDatabase.R;
import com.jsw.MngProductDatabase.database.Contract;
import com.jsw.MngProductDatabase.interfaces.IPharmacyPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pharmacy_Fragment extends Fragment implements IPharmacyPresenter.View {

    private FloatingActionButton mFab;
    private IPharmacyListener mCallback;
    private PharmacyPresenter mPresenter;
    private PharmacyAdapter mAdapter;
    private ListView mList;
    private ProgressDialog mProgress;

    public Pharmacy_Fragment() {
        // Required empty public constructor
    }

    public static Pharmacy_Fragment getInstance() {
        return new Pharmacy_Fragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (IPharmacyListener) activity;
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new PharmacyAdapter(getContext(), null, 1);
        mPresenter = new PharmacyPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_pharmacy, container, false);
        mFab = (FloatingActionButton) rootview.findViewById(R.id.fab_add_pharmacy);
        mProgress = new ProgressDialog(getContext());
        mList = (ListView)rootview.findViewById(R.id.lv_pharmacy);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.addPharmacy();
            }
        });
        mList.setAdapter(mAdapter);
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();
        //Una vez que la vista ha sido creada:
        mPresenter.getAllPharmacies(mAdapter);
    }

    @Override
    public void setPharmacyCategory(Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    public interface IPharmacyListener {
        void addPharmacy();
    }


}
