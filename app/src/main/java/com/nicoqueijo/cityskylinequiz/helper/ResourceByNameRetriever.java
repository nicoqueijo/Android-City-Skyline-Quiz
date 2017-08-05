package com.nicoqueijo.cityskylinequiz.helper;

import android.content.Context;

public class ResourceByNameRetriever {
    /**
     * Retrieves string resources using a String instead of an int.
     *
     * @param name name of the string resource
     * @return the string resource
     */
    public static String getStringResourceByName(String name, Context context) {
        int resId = context.getResources().getIdentifier(name, "string", context.getPackageName());
        return context.getString(resId);
    }

    /**
     * Retrieves drawable resources using a String instead of an int.
     *
     * @param name name of the drawable resource
     * @return the drawable resource id
     */
    public static int getDrawableResourceByName(String name, Context context) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
