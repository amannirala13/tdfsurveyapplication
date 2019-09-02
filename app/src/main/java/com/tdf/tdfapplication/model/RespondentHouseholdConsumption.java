package com.tdf.tdfapplication.model;

public class RespondentHouseholdConsumption {
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    private String frequency;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String source;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

    public RespondentHouseholdConsumption(){
        this.frequency = "666";
        this.source = "666";
        this.price = "666";
    }

    public RespondentHouseholdConsumption(String frequency,String source,String price){
        this.frequency = frequency;
        this.source = source;
        this.price = price;
    }
}
