package com.nicoqueijo.cityskylinequiz.models;

import java.io.Serializable;

/**
 * The model class for each city object. Each city has a name, a country, a URl that is used to
 * fetch its image file from the cloud, GPS coordinates to locate it on a mapping application, and
 * a URL to fetch its Wikipedia article. There are two additional fields to store the city and country
 * name in the language the application is running on. This is used to properly sort the objects by
 * city or country name.
 *
 * Serializable must be implemented in order to pass City objects between intents.
 */
public class City implements Serializable {

    private final static int LATITUDE = 0;
    private final static int LONGITUDE = 1;

    private String cityName;
    private String countryName;
    private String imageUrl;
    private String coordinates;
    private String wikiUrl;

    // These fields are needed to sort in any language in the activity that contains the RecyclerView
    private String cityNameInCurrentLanguage;
    private String countryNameInCurrentLanguage;

    /**
     * Constructor for the City object
     *
     * @param cityName    the city name as it appears in the JSON object
     * @param countryName the country name as it appears in the JSON object
     * @param imageUrl    the URL of the city image as it appears in the JSON object
     * @param coordinates the coordinates of the city as it appears in the JSON object
     * @param wikiUrl     the URL of the Wikipedia article as it appears in the JSON object
     */
    public City(String cityName, String countryName, String imageUrl, String coordinates, String wikiUrl) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.imageUrl = imageUrl;
        this.coordinates = coordinates;
        this.wikiUrl = wikiUrl;
    }

    /**
     * Accessor for the city name
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Mutator for the city name
     *
     * @param cityName the new city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Accessor for the country name
     *
     * @return the country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Mutator for the country name
     *
     * @param countryName the new country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Accessor for the city image URL
     *
     * @return the city image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Mutator for the city image URL
     *
     * @param imageUrl the new city image URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Accessor the the city coordinates
     *
     * @return the city coordinates
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Mutator for the city coordinates
     *
     * @param coordinates the new city coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Accessor for the city Wikipedia URL
     *
     * @return the city Wikipedia URL
     */
    public String getWikiUrl() {
        return wikiUrl;
    }

    /**
     * Mutator for the city Wikipedia URL
     *
     * @param wikiUrl the new city Wikipedia URL
     */
    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    /**
     * Splits the coordinates String dividing it separately into latitude and longitude using the
     * comma as a delimiter.
     * For example: "34.5325327,89.13958674" ---> ["34.5325327", "89.13958674"]
     *
     * @return the coordinates as an array of size 2 which contains the latitude at index 0 and
     * the longitude at index 1.
     */
    private String[] splitCoordinates() {
        return coordinates.split(",");
    }

    /**
     * Accessor for the latitude of the city
     *
     * @return the latitude of the city
     */
    public String getLatitude() {
        return splitCoordinates()[LATITUDE];
    }

    /**
     * Accessor the for longitude of the city
     *
     * @return the longitude of the city
     */
    public String getLongitude() {
        return splitCoordinates()[LONGITUDE];
    }

    /**
     * Accessor for the city name in the current language
     *
     * @return the city name in the current language
     */
    public String getCityNameInCurrentLanguage() {
        return cityNameInCurrentLanguage;
    }

    /**
     * Mutator for the city name in the current language
     *
     * @param cityNameInCurrentLanguage the city name in the current current language
     */
    public void setCityNameInCurrentLanguage(String cityNameInCurrentLanguage) {
        this.cityNameInCurrentLanguage = cityNameInCurrentLanguage;
    }

    /**
     * Accessor for the country name in the current language
     *
     * @return the country name in the current language
     */
    public String getCountryNameInCurrentLanguage() {
        return countryNameInCurrentLanguage;
    }

    /**
     * Mutator for the country name in the current language
     *
     * @param countryNameInCurrentLanguage the country name in the current language
     */
    public void setCountryNameInCurrentLanguage(String countryNameInCurrentLanguage) {
        this.countryNameInCurrentLanguage = countryNameInCurrentLanguage;
    }
}
