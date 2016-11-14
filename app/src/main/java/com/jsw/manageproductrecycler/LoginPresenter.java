package com.jsw.manageproductrecycler;

import android.content.Context;
import android.content.Intent;

import com.jsw.manageproductrecycler.Model.Error;
import com.jsw.manageproductrecycler.interfaces.IValidateAccount;
import com.jsw.manageproductrecycler.utils.ErrorMapUtils;

import java.util.Map;

/**
 * Created by joselu on 19/10/16.
 * Login Presenter.
 * Checks user and password fields.
 */


public class LoginPresenter implements IValidateAccount.IPresenter{

    private  IValidateAccount.View view;
    private int validateUser;
    private int validatePassword;
    private Context context;

    public LoginPresenter(IValidateAccount.View view){
        this.view = view;
        this.context = (Context)view;
    }


    public void validateCredentialsLogin(String user, String Password){

        validateUser = IValidateAccount.IPresenter.validateUser(user);
        validatePassword = IValidateAccount.IPresenter.validatePassword(Password);

        if(validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                /*
                    Por logica los startActivity siempre en las activitys, si no queremos abrirlo
                    desde las activitys los hacemos con view.startActivity
                 */
                Intent i = new Intent(context, Login_Activity.class);
                view.startActivity(i);
            }
            else{
                String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validatePassword));
                view.setMessageError(nameIdMessage, R.id.til_password);
            }
        }
        else{
            String nameIdMessage = ErrorMapUtils.getErrorMap(this.context).get(String.valueOf(validateUser));
            view.setMessageError(nameIdMessage, R.id.til_user);
        }


    }

}
