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

import android.util.Patterns;

import com.jsw.manageproductrecycler.interfaces.ISignUp;
import com.jsw.manageproductrecycler.interfaces.IValidateAccount;

import java.util.regex.Pattern;

/**
 * Created by usuario on 16/11/16.
 */

public class SignUpPresenter implements ISignUp.IPresenterUser, ISignUp.IPresenter{

    private int validateCredentials(){
        String mail = mTilMail.getEditText().getText().toString();
        String username = mTilUsername.getEditText().getText().toString();
        String pass = mTilPassword.getEditText().getText().toString();

        Pattern name = Pattern.compile("[a-zA-Z0-9]{1,255}");
        Pattern password = Pattern.compile("[a-zA-Z0-9]{1,20}");

        int res = 0;

        if(Pattern.matches(name.toString(), username))
            if (Pattern.matches(password.toString(), pass))
                if (Pattern.matches(Patterns.EMAIL_ADDRESS.toString(), mail))
                    res = 0;
                else
                    res = 3;
            else res = 2;
        else
            res = 1;

        return res;
    }
}
