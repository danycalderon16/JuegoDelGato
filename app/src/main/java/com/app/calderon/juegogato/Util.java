package com.app.calderon.juegogato;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class Util {

    public static final int PLAYER_ONE_VS_PLAYER_TWO = 436;
    public static final int PLAYER_ONE_VS_COMPUTER = 437;

    public static void goBack(Context context,Class<?> classToBack){
        Intent intent = new Intent(context,classToBack);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void saveSettingsPlayer(SharedPreferences pref, int mode) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("players", mode);
        editor.apply();
    }

    public static int getSettingsPlayer(SharedPreferences pref){
        return pref.getInt("players",PLAYER_ONE_VS_PLAYER_TWO);
    }

    public static void saveCounterP1(SharedPreferences pref, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("countP1", value);
        editor.apply();
    }

    public static void saveCounterP2(SharedPreferences pref, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("countP2", value);
        editor.apply();
    }

    public static void saveCounterTied(SharedPreferences pref, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("tied", value);
        editor.apply();
    }

    public static int getCounterP1Saved(SharedPreferences pref, int value) {
        return pref.getInt("countP1", value);
    }

    public static int getCounterP2Saved(SharedPreferences pref, int value) {
        return pref.getInt("countP2", value);
    }

    public static int getCounterTiedSaved(SharedPreferences pref, int value) {
        return pref.getInt("tied", value);
    }


}
