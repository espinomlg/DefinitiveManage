package com.jsw.manageproductrecycler.Presenter;

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

import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.ProductRepository;
import com.jsw.manageproductrecycler.interfaces.IProductPresenter;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductPresenter implements IProductPresenter{
    View view;
    private ProductRepository repository;
    public ProductPresenter(IProductPresenter.View Vista){
        this.view = Vista;
        this.repository = ProductRepository.getInstance();
    }

    @Override
    public void loadProducts() {
        if(repository.getProducts().isEmpty())
            view.showEmptyState(true);
        else
            view.showProduct(repository.getProducts());
    }

    @Override
    public Product getProduct(int id) {
        //return repositoty.getProduct(id);
        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        repository.deleteProduct(product);

        //Vuelve a cargar los productos y actualiza los productos.
        loadProducts();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
