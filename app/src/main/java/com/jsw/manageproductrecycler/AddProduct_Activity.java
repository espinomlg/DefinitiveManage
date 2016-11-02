package com.jsw.manageproductrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddProduct_Activity extends AppCompatActivity {

    TextInputLayout mName, mTrademark, mDosage, mStock, mPrice, mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        mName = (TextInputLayout) findViewById(R.id.til_nombre);
        mTrademark = (TextInputLayout) findViewById(R.id.til_marca);
        mDosage = (TextInputLayout) findViewById(R.id.til_dosage);
        mStock = (TextInputLayout) findViewById(R.id.til_stock);
        mPrice = (TextInputLayout) findViewById(R.id.til_price);
        mDescription = (TextInputLayout) findViewById(R.id.til_descripcion);

    }

    public void guardar(View v){
        Intent intent = getIntent();

        intent.putExtra("Name", mName.getEditText().getText().toString());
        intent.putExtra("Trademark", mTrademark.getEditText().getText().toString());
        intent.putExtra("Dosage", mDosage.getEditText().getText().toString());
        intent.putExtra("Stock", mStock.getEditText().getText().toString());
        intent.putExtra("Price", mPrice.getEditText().getText().toString());
        intent.putExtra("Description", mDescription.getEditText().getText().toString());

        setResult(RESULT_OK, intent);
        finish();
    }
}
