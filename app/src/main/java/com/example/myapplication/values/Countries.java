package com.example.myapplication.values;

import java.util.Objects;

public class Countries {

    private String country;

    private int flag;

    public Countries(String country, int flag) {
        this.country = country;
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Countries countries = (Countries) o;

        if (flag != countries.flag) return false;
        return Objects.equals(country, countries.country);
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + flag;
        return result;
    }
}
