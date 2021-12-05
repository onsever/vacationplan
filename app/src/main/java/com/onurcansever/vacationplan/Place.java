package com.onurcansever.vacationplan;

public class Place {
    private String countryName;
    private String placeName;
    private double chargeAmount;
    private int[] images;
    private String shortDesc;
    private String longDesc;

    public Place(String countryName, String placeName, double chargeAmount, int[] images, String shortDesc, String longDesc) {
        this.countryName = countryName;
        this.placeName = placeName;
        this.chargeAmount = chargeAmount;
        this.images = images;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public int[] getImages() {
        return images;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }
}
