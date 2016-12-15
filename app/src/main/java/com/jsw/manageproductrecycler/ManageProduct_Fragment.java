package com.jsw.manageproductrecycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.Presenter.ProductPresenter;
import com.jsw.manageproductrecycler.interfaces.IProduct;

import static com.jsw.manageproductrecycler.interfaces.IProduct.EDITED_KEY;
import static com.jsw.manageproductrecycler.interfaces.IProduct.PRODUCT_KEY;

public class ManageProduct_Fragment extends Fragment {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription;
    ImageView mImage;
    Product p;
    ProductPresenter mPresenter;

    public static ManageProduct_Fragment getInstance(Bundle args){
        ManageProduct_Fragment fragment = new ManageProduct_Fragment();
        if(args != null)
            fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.p = getArguments().getParcelable(IProduct.PRODUCT_KEY);
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
        mDescription = (TextInputLayout) rootView.findViewById(R.id.til_descripcion);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(p != null){
            mImage.setImageResource(p.getmImage());
            mName.getEditText().setText(p.getmName());
            mTrademark.getEditText().setText(p.getmBrandM());
            mDosage.getEditText().setText(p.getmDosage());
            mStock.getEditText().setText(p.getmStock());
            mPrice.getEditText().setText(String.valueOf(p.getmPrice()));
            mDescription.getEditText().setText(p.getmDescription());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    /*public void guardar(View v){

        Intent intent = getIntent();
        intent.putExtra(PRODUCT_KEY, p);
        intent.putExtra(EDITED_KEY, new Product(
                        R.drawable.pastilla,
                        mName.getEditText().getText().toString(),
                        mTrademark.getEditText().getText().toString(),
                        mDosage.getEditText().getText().toString(),
                        mStock.getEditText().getText().toString(),
                        Double.parseDouble(mPrice.getEditText().getText().toString()),
                        mDescription.getEditText().getText().toString()));

        setResult(RESULT_OK, intent);
        finish();
    }*/
}
