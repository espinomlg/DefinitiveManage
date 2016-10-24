package com.jsw.manageproductrecycler;

import android.app.Application;

import com.jsw.manageproductrecycler.Model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by usuario on 20/10/16.
 */

public class Product_Application extends Application {
    private ArrayList<Product> products;

    @Override
    public void onCreate() {
        products = new ArrayList<Product>();
        products.add(new Product(1, 25, R.drawable.pastilla, "Ibuprofeno", "Pastillas", "1gr", "genérico", 5.70));
        products.add(new Product(2, 10,  R.drawable.pastilla, "Ibuprofeno", "Pastillas", "700mg", "genérico", 3.70));
        products.add(new Product(3, 8, R.drawable.vaporu, "Vaporú", "Gel", "1gr", "Vicks", 10.70));
        products.add(new Product(4, 58, R.drawable.hemoal, "Hemoal", "Gel", "1gr", "genérico", 8.70));
        products.add(new Product(5, 78, R.drawable.juanola, "Juanolas", "Pastillas", "1gr", "Juanola", 8.70));
        products.add(new Product(6, 115,  R.drawable.pastilla, "Paracetamol", "Pastillas", "1gr", "genérico", 6.0));
        products.add(new Product(7, 36,  R.drawable.pastilla, "Paracetamol", "Pastillas", "700mg", "genérico", 7.80));
        products.add(new Product(8, 74, R.drawable.avril, "Avril", "Gel", "5gr", "Avril", 6.70));
        products.add(new Product(9, 89, R.drawable.avril, "Avril", "Gel", "1gr", "Avril", 9.70));
        products.add(new Product(10, 04, R.drawable.diazepam, "Diazepam", "Pastillas", "1gr", "Lolailo", 15.99));
    }

    public List<Product> getProducts(){
        Collections.sort(products);
        return products;
    }
}
