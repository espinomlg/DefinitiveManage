package com.jsw.manageproductrecycler;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.interfaces.IProduct;

import static com.jsw.manageproductrecycler.interfaces.IProduct.EDITED_KEY;
import static com.jsw.manageproductrecycler.interfaces.IProduct.PRODUCT_KEY;

public class AddProduct_Activity extends AppCompatActivity {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription;
    ImageButton mImage;
    Product p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        mImage = (ImageButton) findViewById(R.id.ib_imagen);
        mName = (TextInputLayout) findViewById(R.id.til_nombre);
        mTrademark = (TextInputLayout) findViewById(R.id.til_marca);
        mDosage = (TextInputLayout) findViewById(R.id.til_dosage);
        mStock = (TextInputLayout) findViewById(R.id.til_stock);
        mPrice = (TextInputLayout) findViewById(R.id.til_price);
        mDescription = (TextInputLayout) findViewById(R.id.til_descripcion);
        p = (Product)getIntent().getExtras().getSerializable(PRODUCT_KEY);

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
                        p.getmImage(),
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
