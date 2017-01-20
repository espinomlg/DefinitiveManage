package com.jsw.MngProductDatabase;

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

import android.app.Application;

import com.jsw.MngProductDatabase.database.DatabaseHelper;

/**
 * Created by usuario on 20/01/17.
 */

public class ManageProductApplication extends Application {

    //private DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.getInstance(this).getDatabase();
    }
}
