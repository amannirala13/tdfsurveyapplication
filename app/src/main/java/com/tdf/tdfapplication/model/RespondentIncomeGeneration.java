package com.tdf.tdfapplication.model;

public class RespondentIncomeGeneration {


    private String earnings;
    private String paidOutCosts;

    public RespondentIncomeGeneration(){
        this.earnings = "666";
        this.paidOutCosts = "666";
    }

    public String getEarnings() {
        return earnings;
    }
    public void setEarnings(String earnings) {
        this.earnings = earnings;
    }

    public String getPaidOutCosts() {
        return paidOutCosts;
    }

    public void setPaidOutCosts(String paidOutCosts) {
        this.paidOutCosts = paidOutCosts;
    }
}
