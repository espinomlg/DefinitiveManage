package com.jsw.manageproductrecycler.Adapter;

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
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsw.manageproductrecycler.Model.Product;
import com.jsw.manageproductrecycler.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by usuario on 18/11/16.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context contexto;
    private boolean ASC = false;

    public ProductAdapter(Context context) {

        //Cuando aqui ponemos un tercer parametro, teneos que entener que el array interno es igual a este.
        //Por eso cuando hacia un clear se borraba el del DAO.
        //Para evitarlo o le hacemos un new ArayList (Lourdes) o a this le hacemos un addAll (Yo)
        super(context, R.layout.item_product);
        this.contexto = context;
        this.addAll(((Product_Repository)context.getApplicationContext()).getProducts());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item = convertView;
        ProductHolder p;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(contexto);

            item = inflater.inflate(R.layout.item_product, null);

            p = new ProductHolder();


            p.img = (ImageView)item.findViewById(R.id.iv_img);
            p.name = (TextView)item.findViewById(R.id.tv_nombre);
            p.stock = (TextView)item.findViewById(R.id.tv_stock);
            p.precio = (TextView)item.findViewById(R.id.tv_precio);

            item.setTag(p);

        }else
            p = (ProductHolder) item.getTag();

        p.img.setImageResource(getItem(position).getmImage());
        p.name.setText(getItem(position).getmName());
        p.stock.setText(getItem(position).getmStock());
        p.precio.setText(getItem(position).getFormattedPrice());

        return item;
    }

    public void sortAlphabetically(){
        ASC = !ASC;

        if(ASC)
            sort(Product.NAME_COMPARATOR);
        else
            sort(Collections.reverseOrder());

        notifyDataSetChanged();
    }

    public void addProduct(Product product){
        add(product);
    }

    public void removeProduct(Product product){
        remove(product);
        notifyDataSetChanged();
    }

    public void updateListProduct(List<Product> products){
        this.clear();
        this.addAll(products);
    }

    private void hideList(boolean hide){

    }

    public void addAt(Product product, int position){
        insert(product, position);
        notifyDataSetChanged();
    }

    public void deleteProduct(Product product){
        remove(product);
        notifyDataSetChanged();
    }

    class ProductHolder{
        ImageView img;
        TextView name;
        TextView stock;
        TextView precio;
    }
}
