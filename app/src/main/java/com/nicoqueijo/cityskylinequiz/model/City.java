package com.nicoqueijo.cityskylinequiz.model;

import java.io.Serializable;

/**
 * The model class for each city object. Each city has a name, a country, a URl that is used to
 * fetch its image file from the cloud, GPS coordinates to locate it on a mapping application, and
 * a URL to fetch its Wikipedia article. There are two additional fields to store the city and country
 * name in the language the application is running on. This is used to properly sort the objects by
 * city or country name.
 * Serializable must be implemented in order to pass a City object between intents.
 */
public class City implements Serializable {

    private final static int LATITUDE = 0;
    private final static int LONGITUDE = 1;

    private String cityName;
    private String countryName;
    private String imageUrl;
    private String coordinates;
    private String wikiUrl;

    private String cityNameInCurrentLanguage;
    private String countryNameInCurrentLanguage;

    /**
     * @param cityName
     * @param countryName
     * @param imageUrl
     * @param coordinates
     * @param wikiUrl
     */
    public City(String cityName, String countryName, String imageUrl, String coordinates, String wikiUrl) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.imageUrl = imageUrl;
        this.coordinates = coordinates;
        this.wikiUrl = wikiUrl;
    }

    /**
     * @return
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return
     */
    public String getWikiUrl() {
        return wikiUrl;
    }

    /**
     * @param wikiUrl
     */
    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    /**
     * @return
     */
    private String[] splitCoordinates() {
        return coordinates.split(",");
    }

    /**
     * @return
     */
    public String getLatitude() {
        return splitCoordinates()[LATITUDE];
    }

    /**
     * @return
     */
    public String getLongitude() {
        return splitCoordinates()[LONGITUDE];
    }

    /**
     * @return
     */
    public String getCityNameInCurrentLanguage() {
        return cityNameInCurrentLanguage;
    }

    /**
     * @param cityNameInCurrentLanguage
     */
    public void setCityNameInCurrentLanguage(String cityNameInCurrentLanguage) {
        this.cityNameInCurrentLanguage = cityNameInCurrentLanguage;
    }

    /**
     * @return
     */
    public String getCountryNameInCurrentLanguage() {
        return countryNameInCurrentLanguage;
    }

    /**
     * @param countryNameInCurrentLanguage
     */
    public void setCountryNameInCurrentLanguage(String countryNameInCurrentLanguage) {
        this.countryNameInCurrentLanguage = countryNameInCurrentLanguage;
    }
}
