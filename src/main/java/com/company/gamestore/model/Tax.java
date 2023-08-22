package com.company.gamestore.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tax {
    //2 characters only
    //primary key
    private String state;

    //not null
    //8 characters total, 2 after the decimal point
    //000000.00
    private BigDecimal rate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return state.equals(tax.state) && rate.equals(tax.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "Tax{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
