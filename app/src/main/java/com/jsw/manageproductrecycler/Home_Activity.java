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

import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Home_Activity extends AppCompatActivity implements ListProduct_Fragment.IListProductListener {

    private ListProduct_Fragment mListProduct;
    private ManageProduct_Fragment mManageProduct;
    private boolean salir = false;
    private long puls1 = 0;
    private long puls2 = 10;
    private FrameLayout fl_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fl_home = (FrameLayout)findViewById(R.id.fl_frameHome);
        mListProduct = new ListProduct_Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_frameHome, mListProduct).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listproduct, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        /*if(salir)
            puls2 = SystemClock.currentThreadTimeMillis() / 1000;

        if (getSupportFragmentManager().getBackStackEntryCount() > 1 || (puls2 - puls1) < 3)
            super.onBackPressed();
        else {
            Toast.makeText(this, "Pulse otra vez para salir", Toast.LENGTH_SHORT).show();
            salir = !salir;
            puls1 = SystemClock.currentThreadTimeMillis();
        }*/

        int hola = getSupportFragmentManager().getBackStackEntryCount();

        if(getSupportFragmentManager().getBackStackEntryCount() > 0 || salir)
            super.onBackPressed();
        else {
            salir = true;
            Snackbar.make(fl_home, "Press back again to exit", Snackbar.LENGTH_LONG)
                    .setCallback(new Snackbar.Callback() {
                        @Override
                        public void onDismissed(Snackbar snackbar, int event) {
                            super.onDismissed(snackbar, event);
                            salir = false;
                        }
                    }).show();
        }

    }

    @Override
    public void showManageProduct(Bundle bundle) {
        mManageProduct = new ManageProduct_Fragment();
        FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fl_frameHome, mManageProduct);
        fr.addToBackStack("Manage");
        fr.commit();
    }
}
