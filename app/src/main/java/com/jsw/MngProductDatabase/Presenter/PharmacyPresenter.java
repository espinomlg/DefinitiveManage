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

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;

import com.jsw.MngProductDatabase.Cursors.PharmacyCursorLoader;
import com.jsw.MngProductDatabase.interfaces.IPharmacyPresenter;

/**
 * Created by usuario on 30/01/17.
 */

public class PharmacyPresenter implements IPharmacyPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    IPharmacyPresenter.View view;
    private final static int PHARMACY=1;
    private Context context;

    public PharmacyPresenter(IPharmacyPresenter.View Vista){
        this.view = Vista;
        this.context = Vista.getContext();
    }

    @Override
    public void getAllPharmacies(CursorAdapter adapter) {
        ((Activity)context).getLoaderManager().initLoader(PHARMACY, null, this);
    }

    public void restartLoader(){
        ((Activity)context).getLoaderManager().restartLoader(PHARMACY, null, this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        PharmacyCursorLoader ccl;

        switch (id){
            case PHARMACY:
                ccl = new PharmacyCursorLoader(context);
                break;
            default:
                ccl=null;
        }

        return ccl;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setPharmacyCategory(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setPharmacyCategory(null);
    }
}
