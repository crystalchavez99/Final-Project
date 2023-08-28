package com.company.gamestore.viewmodel;

import com.company.gamestore.model.Invoice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InvoiceViewModel implements Serializable {

    @NotNull(message= "Name cannot be null")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    private String name;

    @NotNull(message= "Street cannot be null")
    @Size(max = 100, message = "Cannot be more than 100 characters")
    private String street;


    @NotNull(message= "City cannot be null")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    private String city;


    @NotNull(message= "State cannot be null")
    @Size(max = 20, message = "Cannot be more than 20 characters")
    private String state;


    @NotNull(message= "Zipcode cannot be null")
    @Size(max = 10, message = "Cannot be more than 10 characters")
    private String zipcode;


    @NotNull(message= "itemType cannot be null")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    private String itemType;


    @NotNull(message= "Item ID cannot be null")
    private int itemId;


    // column name quantity
    //not null
    @NotNull(message= "Quantity cannot be null")
    private int quantity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getItemId() == that.getItemId() && getQuantity() == that.getQuantity() && Objects.equals(getName(), that.getName()) && Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getState(), that.getState()) && Objects.equals(getZipcode(), that.getZipcode()) && Objects.equals(getItemType(), that.getItemType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipcode(), getItemType(), getItemId(), getQuantity());
    }
}
