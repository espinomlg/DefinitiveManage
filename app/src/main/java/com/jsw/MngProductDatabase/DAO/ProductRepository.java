package com.jsw.MngProductDatabase.DAO;

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

import com.jsw.MngProductDatabase.Model.Product;
import com.jsw.MngProductDatabase.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */
public class ProductRepository {
    private static ArrayList<Product> products;

    private static ProductRepository ourInstance = new ProductRepository();

    public static ProductRepository getInstance() {
        return ourInstance;
    }

    private ProductRepository() {
        products = new ArrayList<Product>();
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "1gr", "2", 45.70, "Ibuprofeno Generico 1gr"));
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "700mg", "65", 15.70, "Ibuprofeno Generico 700mg"));
        products.add(new Product(R.drawable.vaporu, "Vaporu", "Vicks", "1gr", "6", 65.70, "Gel Utopico"));
        products.add(new Product(R.drawable.hemoal, "Hemoal", "Hemoal SA", "3gr", "2", 55.70, "Crema Almorranas"));
        products.add(new Product(R.drawable.juanola, "Juanolas", "Juanolas", "5gr", "2", 45.70, "Pastillas"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "1gr", "9", 7.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "5gr", "12", 15.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.diazepam, "Diazepam", "Bayern", "1gr", "200", 45.70, "Pastillas Tranquilizantes"));
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "1gr", "2", 45.70, "Ibuprofeno Generico 1gr"));
        products.add(new Product(R.drawable.pastilla, "Ibuprofeno", "Generico", "700mg", "65", 15.70, "Ibuprofeno Generico 700mg"));
        products.add(new Product(R.drawable.vaporu, "Vaporu", "Vicks", "1gr", "6", 65.70, "Gel Utopico"));
        products.add(new Product(R.drawable.hemoal, "Hemoal", "Hemoal SA", "3gr", "2", 55.70, "Crema Almorranas"));
        products.add(new Product(R.drawable.juanola, "Juanolas", "Juanolas", "5gr", "2", 45.70, "Pastillas"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "1gr", "9", 7.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.avril, "Avril", "Avril", "5gr", "12", 15.70, "Crema Quemaduras"));
        products.add(new Product(R.drawable.diazepam, "Diazepam", "Bayern", "1gr", "200", 45.70, "Pastillas Tranquilizantes"));
    }

    public static void add(Product p){
            products.add(p);
    }

    public static List<Product> getProducts(){
        return products;
    }

    public static void OrderAlph(boolean order){
        //if(order)
        //Collections.sort(products, (p1, p2) -> p1.getmName().compareTo(p2.getmName()));
        //else
        //Collections.sort(products, (p1, p2) -> p2.getmName().compareTo(p1.getmName()));
    }

    public static void deleteProduct(Product product){
        products.remove(product);
    }
}
