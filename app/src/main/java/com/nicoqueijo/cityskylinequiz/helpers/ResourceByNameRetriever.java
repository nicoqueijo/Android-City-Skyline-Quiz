package com.nicoqueijo.cityskylinequiz.helpers;

import android.content.Context;

/**
 * Provides two methods to retrieve resources by their name instead of their int ids.
 */
public class ResourceByNameRetriever {
    /**
     * Retrieves string resources using a String instead of an int.
     *
     * @param name    name of the string resource
     * @param context the context from which this method is being called
     * @return the string resource
     */
    public static String getStringResourceByName(String name, Context context) {
        int resId = context.getResources().getIdentifier(name, "string", context.getPackageName());
        return context.getString(resId);
    }

    /**
     * Retrieves drawable resources using a String instead of an int.
     *
     * @param name    name of the drawable resource
     * @param context the context from which this method is being called
     * @return the drawable resource id
     */
    public static int getDrawableResourceByName(String name, Context context) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
