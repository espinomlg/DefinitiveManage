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
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Clase que guarda el esquema de la
 */

public final class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="ManageProduct.db";
    private static DatabaseHelper databaseHelper;
    private Context context;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //Es null porque no vamos a usar cursores
    }

    public static synchronized DatabaseHelper getInstance(Context context){
        if(databaseHelper == null)
            databaseHelper = new DatabaseHelper(context.getApplicationContext()); //Se usa el de la aplicación por si la activity fuese null

        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Contract.CategoryEntry.SQL_CREATE_ENTRIE);
        sqLiteDatabase.execSQL(Contract.ProductEntry.SQL_CREATE_ENTRIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(Contract.ProductEntry.SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(Contract.CategoryEntry.SQL_DELETE_ENTRIES);
        this.onCreate(sqLiteDatabase);
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
