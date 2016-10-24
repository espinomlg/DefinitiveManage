package com.jsw.manageproductrecycler;

import android.content.Context;

import java.util.regex.Pattern;
/**
 * Created by joselu on 19/10/16.
 * Login Presenter.
 * Checks user and password fields.
 */


public class LoginPresenter implements ILogin.Presenter {

    private  ILogin.View mView;
    private Pattern p1 = Pattern.compile("[a-zA-Z0-9]{8,30}");
    private Pattern p2 = Pattern.compile("[A-Z]");
    private Pattern p3 = Pattern.compile("[0-9]");

    public LoginPresenter(ILogin.View view){
        this.mView = view;
    }

    @Override
    public void validateCredentials(String User, String Password) {
        String msgOut = "";
        int idOut = 0;

        if(!User.isEmpty()) //If User is null
            if(!Password.isEmpty())//If Password is null
                if (Password.matches(".*" + p1 + ".*")) //Checks Password lenght (Min 8 chars)
                    if (Password.matches(".*" + p2 + ".*")) //Checks if contains an UpperCase (Min 1)
                        if (Password.matches(".*" + p3 + ".*")) { //Check if contains a Digit (Min 1)
                            msgOut = ((Context)mView).getResources().getString(R.string.login_ok); //If all is correct
                            //((RelativeLogin_Application)((Context)mView).getApplicationContext()).setUser(new User(User, Password));
                        }
                        else { //No digits
                            msgOut = ((Context) mView).getResources().getString(R.string.passDigit);
                            idOut = R.id.et_passwd;
                        }
                    else { //No upper cases
                        msgOut = ((Context) mView).getResources().getString(R.string.passCase);
                        idOut = R.id.et_passwd;
                    }
                else { //Incorrect length
                    msgOut = ((Context) mView).getResources().getString(R.string.passMinLength);
                    idOut = R.id.et_passwd;
                }
            else{ //Empty Pass
                msgOut = ((Context)mView).getResources().getString(R.string.data_empty);
                idOut = R.id.et_passwd;
            }
        else { //Empty User
            msgOut = ((Context) mView).getResources().getString(R.string.data_empty);
            idOut = R.id.et_user;
        }


        mView.setMessageError(msgOut, idOut);
    }
}
