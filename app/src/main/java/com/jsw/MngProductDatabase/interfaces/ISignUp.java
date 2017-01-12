package com.jsw.MngProductDatabase.interfaces;

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

/**
 * Created by usuario on 11/11/16.
 */

public interface ISignUp extends IValidateAccount {

    interface IPresenterUser {

        int validateEmail(String email);

        /*static int validateEmail(String email){
            int result = 0;

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                result = Error.INVALID_MAIL;

            return 0;
        }*/
    }
}
