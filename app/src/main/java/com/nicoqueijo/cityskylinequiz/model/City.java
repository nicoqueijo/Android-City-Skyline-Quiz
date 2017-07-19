package com.nicoqueijo.cityskylinequiz.model;

import java.io.Serializable;

/**
 *
 */
public class City implements Serializable {

    private String cityName;
    private String countryName;
    private String imageUrl;

    /**
     * @param cityName
     * @param countryName
     * @param imageUrl
     */
    public City(String cityName, String countryName, String imageUrl) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.imageUrl = imageUrl;
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
}
