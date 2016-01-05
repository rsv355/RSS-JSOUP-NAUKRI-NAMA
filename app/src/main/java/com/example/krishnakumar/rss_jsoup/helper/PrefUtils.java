package com.example.krishnakumar.rss_jsoup.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class PrefUtils {

    // 0 - for English
    // 1 - for Hindi
    public static void saveLanguage (Context ctx,int lang){
        Prefs.with(ctx).save("language", lang);
    }

    public static int getLanguage(Context ctx){
        int lang;
        lang = Prefs.with(ctx).getInt("language", 0);
        return lang;
    }


    public static String getBaseURL(Context ctx){
        String url;
        if(getLanguage(ctx)==0){
            url = ""+AppConstants.BASE_URL; // for english
        }else{
            url = ""+AppConstants.BASE_URL+"hi/";  // for hindi
        }
        return url;
    }


}
