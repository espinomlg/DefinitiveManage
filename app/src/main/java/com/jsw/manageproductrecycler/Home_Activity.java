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

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.Menu;

public class Home_Activity extends AppCompatActivity implements ListProduct_Fragment.IListProductListener {

    private ListProduct_Fragment mListProduct;
    private ManageProduct_Fragment mManageProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mListProduct = new ListProduct_Fragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_frameHome, mListProduct).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listproduct, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public void showManageProduct(Bundle bundle) {
        mManageProduct = new ManageProduct_Fragment();
        FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fl_frameHome, mListProduct);
        fr.addToBackStack(null);
        fr.commit();
    }
}
