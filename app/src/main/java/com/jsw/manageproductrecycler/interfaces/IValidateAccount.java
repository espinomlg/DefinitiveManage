package com.jsw.manageproductrecycler.interfaces;

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

import com.jsw.manageproductrecycler.R;

import java.util.regex.Pattern;

/**
 * Created by usuario on 11/11/16.
 */

public interface IValidateAccount {

    int OK = 0;
    int PASSDIGIT = 1;
    int PASSCASE = 2;
    int PASSMINLENGTH = 3;
    int DATAEMPTY = 4;

    interface msgView{
        public void setMessageError(String error, int errCode);
    }

    public interface IPresenter{
        //ILogin.View mView;
        Pattern p1 = Pattern.compile("[a-zA-Z0-9]{8,30}");
        Pattern p2 = Pattern.compile("[A-Z]");
        Pattern p3 = Pattern.compile("[0-9]");


        static int validateUser(String User) {
            int idOut = 0;

            if(User.isEmpty()) {//If User is null
                idOut = DATAEMPTY;
            }

            return idOut;
        }

        static int validatePassword(String Password) {
            int idOut = 0;

            if (Password.isEmpty())//If Password is null
                idOut = DATAEMPTY;
            else if (!(Password.matches(".*" + p1 + ".*")))
                idOut = PASSMINLENGTH;
            else if (!(Password.matches(".*" + p2 + ".*")))
                idOut = PASSCASE;
            else if (((Password.matches(".*" + p3 + ".*"))))
                idOut = PASSDIGIT;

            return idOut;
        }
    }


}
