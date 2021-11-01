package com.example.learnaboutcountries.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("name")
    private String countryName;
    @SerializedName("capital")
    private String capitalName;
    @SerializedName("flagPNG")
    private String flag;
    @SerializedName("population")
    private int population;
    @SerializedName("area")
    private double area;
    @SerializedName("region")
    private String region;

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
