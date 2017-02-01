package com.jsw.MngProductDatabase.Fragments;

/*
 * Copyright (c) 2016 José Luis del Pino Gallardo.
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
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.Presenter.CategoryPresenter;
import com.jsw.MngProductDatabase.R;
import com.jsw.MngProductDatabase.database.Contract;
import com.jsw.MngProductDatabase.interfaces.ICategoryPresenter;
import com.jsw.MngProductDatabase.interfaces.IProduct;

public class ManageProduct_Fragment extends Fragment implements ICategoryPresenter.View {

    private static CategoryPresenter mPresenter;
    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription, mUrl;
    ImageView mImage;
    Spinner mCategory;
    Product oldProduct;
    FloatingActionButton mFabSave;
    IManageListener mCallBack;
    private SimpleCursorAdapter mCursorAdapter;

    public static ManageProduct_Fragment getInstance(Bundle args){
        ManageProduct_Fragment fragment = new ManageProduct_Fragment();
        if(args != null)
            fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            this.oldProduct = getArguments().getParcelable(IProduct.PRODUCT_KEY);
        }

        mPresenter = new CategoryPresenter(this);
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
        mPresenter.getAllCategories((CursorAdapter)mCursorAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
        super.onCreateView(inflater, container,  args);
        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);
        mImage = (ImageView) rootView.findViewById(R.id.ib_imagen);
        mName = (TextInputLayout) rootView.findViewById(R.id.til_nombre);
        mTrademark = (TextInputLayout) rootView.findViewById(R.id.til_marca);
        mDosage = (TextInputLayout) rootView.findViewById(R.id.til_dosage);
        mStock = (TextInputLayout) rootView.findViewById(R.id.til_stock);
        mPrice = (TextInputLayout) rootView.findViewById(R.id.til_price);
        mUrl = (TextInputLayout) rootView.findViewById(R.id.til_imageurl);
        mDescription = (TextInputLayout) rootView.findViewById(R.id.til_descripcion);
        mFabSave = (FloatingActionButton)rootView.findViewById(R.id.fab_guardar);
        mCategory = (Spinner)rootView.findViewById(R.id.spinner);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (oldProduct != null) {
            ;
            mName.getEditText().setText(oldProduct.getName());
            mTrademark.getEditText().setText(oldProduct.getBrand());
            mDosage.getEditText().setText(oldProduct.getDosage());
            mStock.getEditText().setText(oldProduct.getStock());
            mPrice.getEditText().setText(String.valueOf(oldProduct.getPrice()));
            mUrl.getEditText().setText(oldProduct.getImage());
            mCategory.setSelection(0);
            mDescription.getEditText().setText(oldProduct.getDescription());
        }

        mFabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        String[] from = {Contract.CategoryEntry.COLUMN_NAME};
        int[] to = {android.R.id.text1};

        mCursorAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_spinner_item, null, from, to, 0);
        mCursorAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mCategory.setAdapter(mCursorAdapter);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallBack = (IManageListener)activity;
    }

    private void save(){

        Cursor cursor = ((SimpleCursorAdapter)mCategory.getAdapter()).getCursor();
        cursor.moveToPosition(mCategory.getSelectedItemPosition());

        int id = 0;

        if (oldProduct != null)
            id = oldProduct.getID();


        mCallBack.saveProduct(oldProduct, new Product(
                id,
                mName.getEditText().getText().toString(),
                mDescription.getEditText().getText().toString(),
                mTrademark.getEditText().getText().toString(),
                mDosage.getEditText().getText().toString(),
                Double.valueOf(mPrice.getEditText().getText().toString()),
                mStock.getEditText().getText().toString(),
                mUrl.getEditText().getText().toString(),
                1));
    }

    /**
     * Swap cambia el cursor y devuelve el antiguo sin cerrarlo.
     * Change cambia el cursor y cierra el antiguo.
     * @param cursor
     */
    @Override
    public void setCursorCategory(Cursor cursor) {
        mCursorAdapter.swapCursor(cursor);
    }

    /**
     * Called when the fragment is no longer attached to its activity.  This
     * is called after {@link #onDestroy()}.
     */
    @Override
    public void onDetach() {
        mCallBack = null;
        mCursorAdapter = null; //Cerramos el cursor en on Detach.
        super.onDetach();
    }

    public interface IManageListener {
        void saveProduct(Product oldProduct, Product newProduct);
    }
}
