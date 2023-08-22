package com.company.gamestore.model;

import java.math.BigDecimal;
import java.util.Objects;

//table name invoice
public class Invoice {
    //primary key, auto increment
    //column name invoice_id
    private int id;

    //not null, 50 characters
    private String name;

    //not null, 100 char
    private String street;

    //not null, 50 char
    private String city;

    //not null, 20 char
    private String state;

    //not null, 10 char
    private String zipcode;

    //column name item_type
    //not null, 50 char
    private String itemType;

    //column name item_id
    //not null, links to game console or t_shirt id
    private int itemId;

    //column name unit_price
    //not null, 8 total, 2 after decimal
    private BigDecimal unitPrice;

    //not null
    private int quantity;

    //not null, 8 total, 2 after decimal
    private BigDecimal subtotal;

    //not null, 8 total, 2 after decimal
    //based off rate from tax model?
    private BigDecimal tax;

    //column name processing_fee
    //not null, 8 total, 2 after decimal
    private BigDecimal processingFee;

    //not null, 8 total, 2 after decimal
    private BigDecimal total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && itemId == invoice.itemId && quantity == invoice.quantity && name.equals(invoice.name) && street.equals(invoice.street) && city.equals(invoice.city) && state.equals(invoice.state) && zipcode.equals(invoice.zipcode) && itemType.equals(invoice.itemType) && unitPrice.equals(invoice.unitPrice) && subtotal.equals(invoice.subtotal) && tax.equals(invoice.tax) && processingFee.equals(invoice.processingFee) && total.equals(invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
