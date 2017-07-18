package com.nicoqueijo.cityskylinequiz.helper;

/**
 * Class with one static method to convert a city/country name that is in its filename format
 * to a presentable format.
 */
public class NameFormatter {

    /**
     * Converts a lower-case string with underscores as delimeters to a string with the first
     * letter of each word capitalized and spaces as delimeters.
     * For example: "buenos_aires" --> "Buenos Aires"
     *
     * @param rawName
     * @return the formatted name
     */
    public static String nameFormatter(String rawName) {
        final int SECOND_TO_LAST = 1;
        String[] splitName;
        String formattedName = "";
        splitName = rawName.split("_");
        for (int i = 0; i < splitName.length; i++) {
            formattedName += splitName[i].substring(0, 1).toUpperCase() + splitName[i].substring(1);
            if (i < (splitName.length - SECOND_TO_LAST)) {
                formattedName += " ";
            }
        }
        return formattedName;
    }
}
