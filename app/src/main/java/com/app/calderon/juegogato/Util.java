package com.app.calderon.juegogato;

import android.content.SharedPreferences;

public class Util {

    public static final int PLAYER_ONE_VS_PLAYER_TWO = 436;
    public static final int PLAYER_ONE_VS_COMPUTER = 437;


    public static void saveSettingsPlayer(SharedPreferences pref, int mode) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("players", mode);
        editor.apply();
    }

    public static int getSettingsPlayer(SharedPreferences pref){
        return pref.getInt("players",PLAYER_ONE_VS_PLAYER_TWO);
    }

}
