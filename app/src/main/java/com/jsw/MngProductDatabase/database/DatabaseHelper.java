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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.jsw.MngProductDatabase.ManageProductApplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que guarda el esquema de la
 */

public final class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME="ManageProduct.db";
    private static DatabaseHelper databaseHelper;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;
    private Context context;

    private DatabaseHelper() {
        super(ManageProductApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION); //Es null porque no vamos a usar cursores
        mOpenCounter = new AtomicInteger();
    }

    public static synchronized DatabaseHelper getInstance(){
        if(databaseHelper == null)
            databaseHelper = new DatabaseHelper(); //Se usa el de la aplicación por si la activity fuese null

        return databaseHelper;
    }

    public synchronized SQLiteDatabase openDatabase(){
        if(mOpenCounter.incrementAndGet() == 1)
            mDatabase = getWritableDatabase();

        return mDatabase;
    }

    public synchronized void closeDatabase(){
        if(mOpenCounter.decrementAndGet() == 0)
            mDatabase.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(Contract.CategoryEntry.SQL_CREATE_ENTRIE);
            sqLiteDatabase.execSQL(Contract.PharmacyEntry.SQL_CREATE_ENTRIE);
            sqLiteDatabase.execSQL(Contract.InvoiceStatusEntry.SQL_CREATE_ENTRIE);
            sqLiteDatabase.execSQL(Contract.ProductEntry.SQL_CREATE_ENTRIE);
            sqLiteDatabase.execSQL(Contract.InvoiceEntry.SQL_CREATE_ENTRIE);
            sqLiteDatabase.execSQL(Contract.InvoiceLineEntry.SQL_CREATE_ENTRIE);
            sqLiteDatabase.setTransactionSuccessful();
        } catch(SQLiteException ex){
            Log.e("DATABASE ERROR", ex.getLocalizedMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(Contract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(Contract.InvoiceEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(Contract.ProductEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(Contract.InvoiceStatusEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(Contract.PharmacyEntry.SQL_DELETE_ENTRIES);
            sqLiteDatabase.execSQL(Contract.CategoryEntry.SQL_DELETE_ENTRIES);
            this.onCreate(sqLiteDatabase);
            sqLiteDatabase.setTransactionSuccessful();
        }

        finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly())
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys = ON");
    }

    public SQLiteDatabase getDatabase(){
        return getWritableDatabase();
    }
}
