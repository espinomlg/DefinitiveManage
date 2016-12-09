package com.jsw.manageproductrecycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.Presenter.ProductPresenter;

import static com.jsw.manageproductrecycler.interfaces.IProduct.EDITED_KEY;
import static com.jsw.manageproductrecycler.interfaces.IProduct.PRODUCT_KEY;

public class ManageProduct_Fragment extends Fragment {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription;
    ImageButton mImage;
    Product p;
    ProductPresenter mPresenter;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        super.onCreateView(parent, name, context, attrs);
        mImage = (ImageButton) findViewById(R.id.ib_imagen);
        mName = (TextInputLayout) findViewById(R.id.til_nombre);
        mTrademark = (TextInputLayout) findViewById(R.id.til_marca);
        mDosage = (TextInputLayout) findViewById(R.id.til_dosage);
        mStock = (TextInputLayout) findViewById(R.id.til_stock);
        mPrice = (TextInputLayout) findViewById(R.id.til_price);
        mDescription = (TextInputLayout) findViewById(R.id.til_descripcion);
        try{
            p = (Product)getIntent().getExtras().getParcelable(PRODUCT_KEY); //ORIGGINAL
        }catch (Exception e){

        }


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

    public void guardar(View v){

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
    }
}
