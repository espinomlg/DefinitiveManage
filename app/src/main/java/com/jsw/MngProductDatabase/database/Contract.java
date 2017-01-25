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

import android.provider.BaseColumns;

/**
 * Created by usuario on 20/01/17.
 */

public final class Contract {

    private Contract(){

    }

    public static class CategoryEntry implements BaseColumns{
        public static final String TABLE_NAME="category";
        public static final String COLUMN_NAME="name";
        public static final String SQL_CREATE_ENTRIE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)",
        TABLE_NAME, BaseColumns._ID, COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class ProductEntry implements BaseColumns{
        public static final String TABLE_NAME="product";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_DESCRIPTION="description";
        public static final String COLUMN_BRAND="brand";
        public static final String COLUMN_DOSAGE="dosage";
        public static final String COLUMN_PRICE="price";
        public static final String COLUMN_STOCK="stock";
        public static final String COLUMN_IMAGE="image";
        public static final String COLUMN_IDCATEGORY="idCategory";
        public static final String REFERENCE_ID_CATEGORY=String.format("REFERENCES category (_id) ON UPDATE CASCADE ON DELETE RESTRICT");
        public static final String SQL_CREATE_ENTRIE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT NOT NULL, " + "%s TEXT NOT NULL, " + "%s TEXT NOT NULL, " +
                "%s TEXT NOT NULL, " + "%s INTEGER NOT NULL, " + "%s TEXT NOT NULL, " +
                "%s TEXT NOT NULL, " + "%s INTEGER NOT NULL %s)",
                TABLE_NAME, BaseColumns._ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_BRAND,
                COLUMN_DOSAGE, COLUMN_PRICE, COLUMN_STOCK, COLUMN_IMAGE, COLUMN_IDCATEGORY, REFERENCE_ID_CATEGORY);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        public static final String[] ALL_COLUMNS = {BaseColumns._ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_BRAND, COLUMN_DOSAGE, COLUMN_PRICE, COLUMN_STOCK, COLUMN_IMAGE, COLUMN_IDCATEGORY};
    }

    public static class InvoiceCategory implements BaseColumns{
        public static final String TABLE_NAME="invoice";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_IDPHARMACY="idPharmacy";
        public static final String COLUMN_DATE="date";
        public static final String COLUMN_STATUS="status";
        public static final String SQL_CREATE_ENTRIE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s INT REFERENCES Pharmacy (_id) ON UPDATE CASCADE ON DELETE RESTRICT, %s TEXT, %s INTEGER REFERENCES invoicestatus (_id) ON UPDATE CASCADE ON DELETE RESTRICT)", TABLE_NAME, BaseColumns._ID, COLUMN_NAME, COLUMN_IDPHARMACY, COLUMN_DATE, COLUMN_STATUS);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class InvoiceLineCategory implements BaseColumns{
        public static final String TABLE_NAME="invoiceline";
        public static final String COLUMN_IDINVOICE="idInvoice";
        public static final String COLUMN_ORDERPRODUCT="orderProduct";
        public static final String COLUMN_IDPRODUCT="idProduct";
        public static final String COLUMN_AMOUNT="amount";
        public static final String COLUMN_PRICE="price";
        public static final String SQL_CREATE_ENTRIE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER NOT NULL REFERENCES invoice (_id) ON UPDATE CASCADE ON DELETE RESTRICT, %s INTEGER NOT NULL, %s INT REFERENCES product (_id) ON UPDATE CASCADE ON DELETE RESTRICT, %s INTEGER NOT NULL, %s REAL)",
                TABLE_NAME, BaseColumns._ID, COLUMN_IDINVOICE, COLUMN_ORDERPRODUCT,
                COLUMN_IDPRODUCT, COLUMN_AMOUNT, COLUMN_PRICE);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class InvoiceStatusCategory implements BaseColumns{
        public static final String TABLE_NAME="invoicestatus";
        public static final String COLUMN_NAME="name";
        public static final String SQL_CREATE_ENTRIE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID, COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class PharmacyCategory implements BaseColumns{
        public static final String TABLE_NAME="pharmacy";
        public static final String COLUMN_CIF="cif";
        public static final String COLUMN_ADDRESS="address";
        public static final String COLUMN_PHONE="phone";
        public static final String SQL_CREATE_ENTRIE = String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID, COLUMN_CIF, COLUMN_ADDRESS, COLUMN_PHONE);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }


}
