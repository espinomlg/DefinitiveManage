package com.jsw.MngProductDatabase.Presenter;

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

import android.database.sqlite.SQLiteDatabase;

import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.database.DatabaseHelper;
import com.jsw.MngProductDatabase.database.DatabaseManager;
import com.jsw.MngProductDatabase.interfaces.IProductPresenter;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductPresenter implements IProductPresenter{
    View view;

    public ProductPresenter(IProductPresenter.View Vista){
        this.view = Vista;
    }

    @Override
    public void loadProducts() {
        if(DatabaseManager.getInstance().getProducts().isEmpty())
            view.showEmptyState(true);
        else
            view.showProduct();
    }

    @Override
    public Product getProduct(int id) {
        return DatabaseManager.getInstance().getProducts().get(id);
    }

    @Override
    public void deleteProduct(Product product) {
        SQLiteDatabase sql = DatabaseHelper.getInstance().openDatabase();

        view.showMessage("Product Delete", product);
        loadProducts();

        DatabaseHelper.getInstance().closeDatabase();
    }


    public void addProduct(Product product){
        DatabaseManager.getInstance().addProduct(product);
        view.showProduct();
    }

    public void updateProduct(Product oldProduct, Product newProduct){
        DatabaseManager.getInstance().deleteProduct(oldProduct);
        this.addProduct(newProduct);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
