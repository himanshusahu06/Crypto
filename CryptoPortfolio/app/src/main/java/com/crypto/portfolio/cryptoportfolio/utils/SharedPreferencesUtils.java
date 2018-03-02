package com.crypto.portfolio.cryptoportfolio.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesUtils {

    private static SharedPreferences sharedpreferences;
    private static String PREFERENCE = "CRYPTO_PREFERENCE";

    public static String BITTREX_KEY_NAME = "BITTREX_KEY";
    public static String BITTREX_SECRET_NAME = "BITTREX_SECRET";

    public static String BINANCE_KEY_NAME = "BINANCE_KEY";
    public static String BINANCE_SECRET_NAME = "BINANCE_SECRET";

    public static String get(String key, Context context) {
        sharedpreferences = context.getSharedPreferences(PREFERENCE, context.MODE_PRIVATE);
        return sharedpreferences.getString(key, null);
    }

    public static void setBittrexPreference(String key, String secret) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(BITTREX_KEY_NAME, key);
        editor.putString(BITTREX_SECRET_NAME, secret);
        editor.commit();
    }

    public static void setBinancePreference(String key, String secret) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(BINANCE_KEY_NAME, key);
        editor.putString(BINANCE_SECRET_NAME, secret);
        editor.commit();
    }
}
