package com.jsw.MngProductDatabase.database;

/*
 * Copyright (c) 2017 José Luis del Pino Gallardo.
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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.jsw.MngProductDatabase.Model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 23/01/17.
 */
public class DatabaseManager {
    private static DatabaseManager ourInstance;

    public static DatabaseManager getInstance() {
        if(ourInstance == null)
            ourInstance = new DatabaseManager();

        return ourInstance;
    }

    private DatabaseManager() {
    }

    public void addProduct(Product p){
        SQLiteDatabase sqlite = DatabaseHelper.getInstance().openDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contract.ProductEntry.COLUMN_NAME, p.getName());
        cv.put(Contract.ProductEntry.COLUMN_DESCRIPTION, p.getDescription());
        cv.put(Contract.ProductEntry.COLUMN_BRAND, p.getBrand());
        cv.put(Contract.ProductEntry.COLUMN_DOSAGE, p.getDosage());
        cv.put(Contract.ProductEntry.COLUMN_PRICE, p.getPrice());
        cv.put(Contract.ProductEntry.COLUMN_STOCK, p.getStock());
        cv.put(Contract.ProductEntry.COLUMN_IMAGE, p.getImage());
        cv.put(Contract.ProductEntry.COLUMN_IDCATEGORY, 1);
        sqlite.insert(Contract.ProductEntry.TABLE_NAME, null, cv);

        DatabaseHelper.getInstance().closeDatabase();
    }

    public void updateProduct(Product p){
        SQLiteDatabase sqlite = DatabaseHelper.getInstance().openDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Contract.ProductEntry.COLUMN_NAME, p.getName());
        cv.put(Contract.ProductEntry.COLUMN_DESCRIPTION, p.getDescription());
        cv.put(Contract.ProductEntry.COLUMN_BRAND, p.getBrand());
        cv.put(Contract.ProductEntry.COLUMN_DOSAGE, p.getDosage());
        cv.put(Contract.ProductEntry.COLUMN_PRICE, p.getPrice());
        cv.put(Contract.ProductEntry.COLUMN_STOCK, p.getStock());
        cv.put(Contract.ProductEntry.COLUMN_IMAGE, p.getImage());
        cv.put(Contract.ProductEntry.COLUMN_IDCATEGORY, 1);
        String where = BaseColumns._ID + " = ?";
        String[] args = {String.valueOf(p.getID())};
        sqlite.update(Contract.ProductEntry.TABLE_NAME, cv, where,  args);

        DatabaseHelper.getInstance().closeDatabase();
    }

    public List<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<>();
        Product product;
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(Contract.ProductEntry.TABLE_NAME, Contract.ProductEntry.ALL_COLUMNS, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                product = new Product();
                product.setID(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setDescription(cursor.getString(2));
                product.setBrand(cursor.getString(3));
                product.setDosage(cursor.getString(4));
                product.setPrice(cursor.getDouble(5));
                product.setStock(cursor.getString(6));
                product.setImage(cursor.getString(7));
                product.setIdCategory(cursor.getInt(8));
                products.add(product);
            } while(cursor.moveToNext());

        }

        DatabaseHelper.getInstance().closeDatabase();

        return products;
    }

    public void deleteProduct(Product p){
        SQLiteDatabase sqLite = DatabaseHelper.getInstance().openDatabase();
        String where = BaseColumns._ID + "=?";
        String[] args = {String.valueOf(p.getID())};
        sqLite.delete(Contract.ProductEntry.TABLE_NAME, where, args);
        DatabaseHelper.getInstance().closeDatabase();
    }

    /**
     * Métodos de la tabla categoría.
     */

    public Cursor getAllCategories(){
        SQLiteDatabase sqLite = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLite.query(Contract.CategoryEntry.TABLE_NAME, Contract.CategoryEntry.ALL_COLUMNS, null, null, null, null, null);
        DatabaseHelper.getInstance().closeDatabase();
        return cursor;
    }
}
