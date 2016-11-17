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

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jsw.manageproductrecycler.Presenter.SignUpPresenter;
import com.jsw.manageproductrecycler.interfaces.ISignUp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Activity extends AppCompatActivity implements ISignUp.View {

    RadioGroup mRg;
    EditText mEtEmpresa;
    Spinner mSpnProvincia;
    Spinner mSpnLocalidad;
    TextInputLayout mTilMail;
    TextInputLayout mTilUsername;
    TextInputLayout mTilPassword;
    private SignUpPresenter mPresenter;
    private ViewGroup layout;

    private AdapterView.OnItemSelectedListener mSpinnerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mRg = (RadioGroup)findViewById(R.id.rg_generos);
        mEtEmpresa = (EditText)findViewById(R.id.editText);
        mSpnProvincia = (Spinner)findViewById(R.id.spn_provincia);
        mSpnLocalidad = (Spinner)findViewById(R.id.spn_localidad);
        mTilMail = (TextInputLayout)findViewById(R.id.til_email);
        mTilUsername = (TextInputLayout)findViewById(R.id.til_username);
        mTilPassword = (TextInputLayout)findViewById(R.id.til_password2);
        mPresenter = new SignUpPresenter(this);
        layout = (ViewGroup)findViewById(R.id.activity_sign_up);
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (radioGroup.getCheckedRadioButtonId() == R.id.rb_Empresa)
                    mEtEmpresa.setVisibility(View.VISIBLE);
                else
                    mEtEmpresa.setVisibility(View.GONE);
            }
        });

        loadSpinnerProvincia();
    }

    private void loadSpinnerProvincia(){

        mSpinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner sp = (Spinner) view.getParent();
                switch (sp.getId()){
                    case R.id.spn_provincia:
                        cargarLocalidad(mSpnProvincia.getSelectedItemPosition());
                        break;
                    case R.id.spn_localidad:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        //Inicializar provincias
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, R.layout.support_simple_spinner_dropdown_item);
        mSpnProvincia.setOnItemSelectedListener(mSpinnerListener);
        mSpnProvincia.setAdapter(adapter);
        }

    private void cargarLocalidad(int pos){
        TypedArray aLocalidades = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
        CharSequence[] localidades = aLocalidades.getTextArray(pos);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(SignUp_Activity.this, android.R.layout.simple_spinner_dropdown_item, localidades);
        mSpnLocalidad.setAdapter(adapter);
    }


    /**
     * {@link Resources#getIdentifier(String, String, String)}
     * @param v
     */
    public void registrarse(View v){

        //Recoger los datos de las vista y mandarlos al presentador.

        String mail = mTilMail.getEditText().getText().toString();
        String username = mTilUsername.getEditText().getText().toString();
        String pass = mTilPassword.getEditText().getText().toString();

        mPresenter.validateCredentials(username, pass, mail);
    }

    public void verLocalidad(){
        Toast.makeText(getApplicationContext(), getResources().getString(R.string.double_message,
                mSpnProvincia.getSelectedItem(),
                mSpnLocalidad.getSelectedItem().toString()), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setMessageError(String nameResource, int View) {

        String messageError = getResources().getString(getResources().getIdentifier(nameResource, "string", getPackageName()));

        switch (View) {
            case R.id.til_user: //Incorrect user case
                //mTilUser.setError(error);
                Snackbar.make(layout, messageError, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.til_password: //Incorrect Password Case
                //mTilPass.setError(messageError);
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                break;
            case R.id.til_email:
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
            case 0: //Correct Login
                Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show();
                startActivity();
                break;
        }
    }

    public void startActivity(){
        Intent i = new Intent(this, ManageProduct_Activity.class);
        startActivity(i);
        finish();
    }
}
