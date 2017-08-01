package com.nicoqueijo.cityskylinequiz.helper;

/**
 * Class provides one static method to act accordingly depending on device API level.
 */
public class ApiChecker {

    /**
     * Determines if device is running on Lollipop or higher (API level 21).
     *
     * @return whether the API level of this device is 21 or higher
     */
    public static boolean isRunningLollipopOrHigher() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    }

}
