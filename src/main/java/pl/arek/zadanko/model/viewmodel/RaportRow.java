/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.model.viewmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import pl.arek.zadanko.model.Order;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
public class RaportRow {

    private String name;
    private String surname;
    private float totalAmount;
    private Date lastOrderDate;
    private List<Order> bestThreeOrders;

    public RaportRow() {
        this.bestThreeOrders = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public List<Order> getBestThreeOrders() {
        return bestThreeOrders;
    }

    public void setBestThreeOrders(List<Order> bestThreeOrders) {
        this.bestThreeOrders = bestThreeOrders;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.surname);
        hash = 13 * hash + Float.floatToIntBits(this.totalAmount);
        hash = 13 * hash + Objects.hashCode(this.lastOrderDate);
        hash = 13 * hash + Objects.hashCode(this.bestThreeOrders);
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
        final RaportRow other = (RaportRow) obj;
        if (Float.floatToIntBits(this.totalAmount) != Float.floatToIntBits(other.totalAmount)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.lastOrderDate, other.lastOrderDate)) {
            return false;
        }
        if (!Objects.equals(this.bestThreeOrders, other.bestThreeOrders)) {
            return false;
        }
        return true;
    }

}
