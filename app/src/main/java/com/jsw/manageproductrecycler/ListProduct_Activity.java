package com.jsw.manageproductrecycler;

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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jsw.manageproductrecycler.Adapter.ProductAdapter;
import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.interfaces.IProduct;

import java.util.concurrent.LinkedBlockingDeque;

public class ListProduct_Activity extends AppCompatActivity implements IProduct {

    private ProductAdapter mAdapter;
    private ListView mList;
    int position;
    PopupMenu popup;
    //Inflating the Popup using xml file



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        mList = (ListView) findViewById(R.id.listProduct);
        mAdapter = new ProductAdapter(this);
        mList.setAdapter(mAdapter);


        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(PRODUCT_KEY, (Product) parent.getItemAtPosition(i));
                position = i;
                Intent intent = new Intent(ListProduct_Activity.this, AddProduct_Activity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_PRODUCT);
            }
        });

        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AdapterView<?> parent = adapterView;
                final int pos = i;

                popup  = new PopupMenu(ListProduct_Activity.this, view);
                popup.setGravity(Gravity.RIGHT);
                popup.getMenuInflater().inflate(R.menu.delete_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        mAdapter.deleteProduct((Product) parent.getItemAtPosition(pos));
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_PRODUCT:
                if (resultCode == RESULT_OK) {
                    Product product = (Product) data.getExtras().getSerializable(EDITED_KEY);
                    mAdapter.addProduct(product);
                }
                break;
            case EDIT_PRODUCT:
                if (resultCode == RESULT_OK) {
                    mAdapter.removeProduct((Product)data.getExtras().getSerializable(PRODUCT_KEY));
                    mAdapter.addAt((Product)data.getExtras().getSerializable(EDITED_KEY), position);

                }
        }
    }

    public void añadir(View v){
        Intent intent = new Intent(ListProduct_Activity.this, AddProduct_Activity.class);
        startActivityForResult(intent, ADD_PRODUCT);
    }
}