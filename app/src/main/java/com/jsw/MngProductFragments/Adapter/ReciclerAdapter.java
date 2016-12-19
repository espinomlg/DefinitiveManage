package com.jsw.MngProductFragments.Adapter;

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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsw.MngProductFragments.Model.Product;
import com.jsw.MngProductFragments.DAO.ProductRepository;
import com.jsw.MngProductFragments.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 24/10/16.
 *
 * Los ReciclerView no tienen un array de datos internamente, sino que gestionan una colección de ViewHolder. Que básicamente son las tarjetas con
 * toda la información. Los ArrayAdapter tienen una copia internamente. El RecyclerView no.
 */

public class ReciclerAdapter extends RecyclerView.Adapter<ReciclerAdapter.ProductViewHolder>{

    private List<Product> products;
    private Context context;
    boolean ASC = false;

    public ReciclerAdapter(Context context) {
        this.context = context;
        products = new ArrayList<Product>(ProductRepository.getProducts());
    }

    public void addProduct(Product product){
        ProductRepository.add(product);
        updateView(ProductRepository.getProducts());
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.mProductImg.setImageResource(products.get(position).getmImage());
        holder.mName.setText(products.get(position).getmName());
        holder.mPrice.setText(products.get(position).getFormattedPrice());
        holder.mStock.setText(String.valueOf(products.get(position).getmStock()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }



    /*Como esta clase se va a dedicar sólo a crear vistas y son iguales, la hacemos estática, así su comportamiento
     *es único, y se carga más rápido en memoria.
     */
    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView mProductImg;
        TextView mName;
        TextView mPrice;
        TextView mStock;

        public ProductViewHolder(View item) {
            super(item);
            mProductImg = (ImageView) item.findViewById(R.id.iv_img);
            mName = (TextView) item.findViewById(R.id.tv_nombre);
            mPrice = (TextView) item.findViewById(R.id.tv_precio);
            mStock = (TextView) item.findViewById(R.id.tv_stock);
        }
    }

    public void updateView(List<Product> list){
        products.clear(); //Eliminamos toda la coleccion
        products.addAll(list); //Añadimos toda la coleccion
        notifyDataSetChanged(); //Avisamos a la lista
    }

    public void sortProducts(){
        ASC = !ASC;
        ProductRepository.OrderAlph(ASC);
        updateView(ProductRepository.getProducts());
    }
}
