package com.nicoqueijo.cityskylinequiz.model;

import java.io.Serializable;

/**
 *
 */
public class City implements Serializable {

    private String cityName;
    private String countryName;
    private String imageUrl;
    private String coordinates;
    private String wikiUrl;
    // add fields to separate latitude and longitude from coordinates


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
}
