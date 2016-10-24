package com.jsw.manageproductrecycler;

/**
 * Created by joselu on 19/10/16.
 * Login Interface.
 * Defines Password restrictions.
 * There is a validatePassword method, with a user and a password.
 */

public interface ILogin {
    interface  View{
        public void setMessageError(String error, int idOut);
    }

    interface Presenter {
        int OK = 0;
        int PASSDIGIT = 1;
        int PASSCASE = 2;
        int PASSMINLENGTH = 3;

        public void validateCredentials(String User, String Password);
    }
}