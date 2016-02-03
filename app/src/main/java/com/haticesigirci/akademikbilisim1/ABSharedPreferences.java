package com.haticesigirci.akademikbilisim1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by haticesigirci on 01/02/16.
 */
public class ABSharedPreferences {

    private Context context;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    private static final String PREFERENCES_NAME="com.haticesigirci.akademikbilisim1";
    private static final int PREFERENCES_MODE = Activity.MODE_PRIVATE;

    public ABSharedPreferences(Context context) {

        this.context = context;
        preferences = context.getSharedPreferences(
                PREFERENCES_NAME,
                PREFERENCES_MODE
        );

        editor = preferences.edit();


    }



}
