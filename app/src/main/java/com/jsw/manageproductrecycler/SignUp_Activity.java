package com.jsw.manageproductrecycler;

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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SignUp_Activity extends AppCompatActivity {

    RadioGroup mRg;
    EditText mEtEmpresa;
    Spinner mSpnProvincia;
    Spinner mSpnLocalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mRg = (RadioGroup)findViewById(R.id.rg_generos);
        mEtEmpresa = (EditText)findViewById(R.id.editText);
        mSpnProvincia = (Spinner)findViewById(R.id.spn_provincia);
        mSpnLocalidad = (Spinner)findViewById(R.id.spn_localidad);

        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (radioGroup.getCheckedRadioButtonId() == R.id.rb_Empresa)
                    mEtEmpresa.setVisibility(View.VISIBLE);
                else
                    mEtEmpresa.setVisibility(View.GONE);
            }
        });
    }
}
