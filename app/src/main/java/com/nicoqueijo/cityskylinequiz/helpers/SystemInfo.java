package com.nicoqueijo.cityskylinequiz.helpers;

import java.util.Locale;

/**
 * Class provides fields and methods that give us information on the user's device.
 */
public class SystemInfo {

    public final static String SYSTEM_LOCALE = Locale.getDefault().getLanguage();

    /**
     * Determines if device is running on Lollipop or higher (API level 21).
     *
     * @return whether the API level of this device is 21 or higher
     */
    public static boolean isRunningLollipopOrHigher() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    }
}
