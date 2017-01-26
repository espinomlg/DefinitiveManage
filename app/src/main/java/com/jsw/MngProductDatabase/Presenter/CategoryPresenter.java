package com.jsw.MngProductDatabase.Presenter;

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

import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import com.jsw.MngProductDatabase.database.DatabaseManager;
import com.jsw.MngProductDatabase.interfaces.ICategoryPresenter;

/**
 * Created by usuario on 26/01/17.
 */

public class CategoryPresenter implements ICategoryPresenter {

    private ICategoryPresenter.View view;

    public CategoryPresenter(ICategoryPresenter.View view) {
        this.view = view;
    }

    @Override
    public void getAllCategories(CursorAdapter adapter) {
        Cursor cursor = DatabaseManager.getInstance().getAllCategories();
        adapter.swapCursor(cursor);
    }
}
