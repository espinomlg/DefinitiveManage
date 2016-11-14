package com.jsw.manageproductrecycler;

import android.content.Context;

import com.jsw.manageproductrecycler.interfaces.IValidateAccount;
/**
 * Created by joselu on 19/10/16.
 * Login Presenter.
 * Checks user and password fields.
 */


public class LoginPresenter implements IValidateAccount.IPresenter{

    public LoginPresenter(){

    }

    private  IValidateAccount.msgView view;
    private int validateUser;
    private int validatePassword;

    public void validateCredentialsLogin(String user, String Password){

        validateUser = IValidateAccount.IPresenter.validateUser(user);
        validatePassword = IValidateAccount.IPresenter.validatePassword(Password);

        if(validateUser == IValidateAccount.OK) {
            if (validatePassword == IValidateAccount.OK) {

            }
        }
        else{
            view.setMessageError("Hola", 1);
        }


    }

}
