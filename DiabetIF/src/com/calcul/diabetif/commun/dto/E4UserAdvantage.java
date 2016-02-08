package com.calcul.diabetif.commun.dto;

public class E4UserAdvantage {

    private double mAdvantage;
    private String mFirstName;
    private String mLastName;

    public double getAdvantage() {
        return mAdvantage;
    }

    public void setAdvantage(double mAdvantage) {
        this.mAdvantage = mAdvantage;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getFullName() {
        return capitalize(mFirstName != null ? mFirstName.trim() + " " : "")
                + capitalize(mLastName != null ? mLastName.trim() : "");
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) return "";
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

}
