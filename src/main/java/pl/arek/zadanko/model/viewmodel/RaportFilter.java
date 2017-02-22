/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.model.viewmodel;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public class RaportFilter {

    private Date minDate;
    private Date maxDate;
    private float minprice;
    private float maxprice;
    private String surname;
    private FilterType filterType = FilterType.DEFAULT;

    public enum FilterType {
        DEFAULT, DATE, PRICE, NAME
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public float getMinprice() {
        return minprice;
    }

    public void setMinprice(float minprice) {
        this.minprice = minprice;
    }

    public float getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(float maxprice) {
        this.maxprice = maxprice;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String name) {
        this.surname = name;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.minDate);
        hash = 53 * hash + Objects.hashCode(this.maxDate);
        hash = 53 * hash + Float.floatToIntBits(this.minprice);
        hash = 53 * hash + Float.floatToIntBits(this.maxprice);
        hash = 53 * hash + Objects.hashCode(this.surname);
        hash = 53 * hash + Objects.hashCode(this.filterType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RaportFilter other = (RaportFilter) obj;
        if (Float.floatToIntBits(this.minprice) != Float.floatToIntBits(other.minprice)) {
            return false;
        }
        if (Float.floatToIntBits(this.maxprice) != Float.floatToIntBits(other.maxprice)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.minDate, other.minDate)) {
            return false;
        }
        if (!Objects.equals(this.maxDate, other.maxDate)) {
            return false;
        }
        if (this.filterType != other.filterType) {
            return false;
        }
        return true;
    }
}
