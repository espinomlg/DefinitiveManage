package com.jsw.MngProductFragments;

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

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.FrameLayout;

import com.jsw.MngProductFragments.Fragments.ListProduct_Fragment;
import com.jsw.MngProductFragments.Fragments.ManageProduct_Fragment;
import com.jsw.MngProductFragments.Model.Product;
import com.jsw.MngProductFragments.Presenter.ProductPresenter;
import com.jsw.MngProductFragments.utils.DialogoConfirmacion;

public class Home_Activity extends AppCompatActivity implements ListProduct_Fragment.IListProductListener, ManageProduct_Fragment.IManageListener {

    private ListProduct_Fragment mListProduct;
    ProductPresenter mPresenter;
    private ManageProduct_Fragment mManageProduct;
    private boolean salir = false;
    private FrameLayout fl_home;
    private Bundle mPotatoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fl_home = (FrameLayout)findViewById(R.id.fl_frameHome);
        mListProduct = ListProduct_Fragment.getInstance(null);
        mPresenter = new ProductPresenter(mListProduct);
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
        mManageProduct = ManageProduct_Fragment.getInstance(bundle);
        FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fl_frameHome, mManageProduct);
        fr.addToBackStack("Manage");
        fr.commit();
    }

    @Override
    public void showDeletePopUp(Product product) {
        mPotatoe = new Bundle();
        mPotatoe.putParcelable("product", product);
        DialogoConfirmacion dialog = new DialogoConfirmacion();
        dialog.setArguments(mPotatoe);
        dialog.setPresenter(mPresenter);
        dialog.show(getFragmentManager(), "");
    }

    @Override
    public void undoDeleting(Product product) {
        mPresenter.addProduct(product);
    }

    @Override
    public void saveProduct(Product oldProduct, Product newProduct) {
        if(oldProduct == null)
            mPresenter.addProduct(newProduct);
        else
            mPresenter.updateProduct(oldProduct, newProduct);

        getSupportFragmentManager().popBackStack();
    }



}
