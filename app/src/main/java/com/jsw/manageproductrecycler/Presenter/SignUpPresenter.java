package com.jsw.manageproductrecycler.Presenter;

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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Patterns;

import com.jsw.manageproductrecycler.Login_Activity;
import com.jsw.manageproductrecycler.Model.Error;
import com.jsw.manageproductrecycler.Prefs.AccountPrefs;
import com.jsw.manageproductrecycler.R;
import com.jsw.manageproductrecycler.interfaces.ISignUp;
import com.jsw.manageproductrecycler.interfaces.IValidateAccount;
import com.jsw.manageproductrecycler.utils.ErrorMapUtils;

import java.util.regex.Pattern;

/**
 * Created by usuario on 16/11/16.
 */

public class SignUpPresenter implements ISignUp.IPresenterUser, ISignUp.IPresenter{


    private int validateUser;
    private int validatePassword;
    private int validateMail;
    private ISignUp.View view;
    private Context context;
    private String nameIdMessage;


    public SignUpPresenter(ISignUp.View view){
        this.view = view;
        this.context = (Context)view;
    }


    public void validateCredentials(String username, String pass, String mail){

        validateUser = this.validateUser(username);
        validatePassword = this.validatePassword(pass);
        validateMail = this.validateEmail(mail);

        if(validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                if (validateMail == Error.OK) {
                    //Guardamos las preferencias
                    this.savePreferences(username, pass, mail);
                }
                else{
                    nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateMail));
                    view.setMessageError(nameIdMessage, R.id.til_email);
                }
            }
            else{
                nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.til_password);
            }
        }
        else{
            nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.til_user);
        }
    }

    private void savePreferences(String user, String password, String email){
        AccountPrefs accountPrefs = (AccountPrefs)AccountPrefs.getInstance(this.context);
        accountPrefs.putUser(user);
        accountPrefs.putPassword(password);
        accountPrefs.putMail(email);
    }

    @Override
    public int validateEmail(String email) {
        int result = 0;

        if(!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
            result = Error.INVALID_MAIL;

        return result;
    }

    @Override
    public int validateUser(String User) {
        int idOut = 0;

        if(User.isEmpty()) {//If User is null
            idOut = Error.DATAEMPTY;
        }

        return idOut;
    }

    @Override
    public int validatePassword(String Password) {
        int idOut = 0;

        if (Password.isEmpty())//If Password is null
            idOut = Error.DATAEMPTY;
        else if (!(Password.matches(".*" + p1 + ".*")))
            idOut = Error.PASSMINLENGTH;
        else if (!(Password.matches(".*" + p2 + ".*")))
            idOut = Error.PASSCASE;
        else if (!(Password.matches(".*" + p3 + ".*")))
            idOut = Error.PASSDIGIT;

        return idOut;
    }
}
