package com.jsw.manageproductrecycler.Settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.jsw.manageproductrecycler.R;

/**
 * Created by usuario on 2/11/16.
 */

public class AccountSettings extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.account_settings);
    }
}
