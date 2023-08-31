package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.validation.constraints.*;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice implements Serializable {

    // column name id
    // not null
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int id;

    // column name name
    //not null, 50 characters
    @Column(name  = "name")
    @NotNull(message= "Name cannot be null")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    @NotEmpty(message = "You must supply a value for name.")
    private String name;

    // column name street
    //not null, 100 char
    @Column(name = "street")
    @NotNull(message= "Street cannot be null")
    @Size(max = 100, message = "Cannot be more than 100 characters")
    @NotEmpty(message = "You must supply a value for street.")
    private String street;

    // column name city
    //not null, 50 char
    @Column(name = "city")
    @NotNull(message= "City cannot be null")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    @NotEmpty(message = "You must supply a value for city.")
    private String city;

    // column name state
    //not null, 20 char
    @Column(name = "state")
    @NotNull(message= "State cannot be null")
    @Size(max = 20, message = "Cannot be more than 20 characters")
    @NotEmpty(message = "You must supply a value for state.")
    private String state;

    // column name zipcode
    //not null, 10 char
    @Column(name = "zipcode")
    @NotNull(message= "Zipcode cannot be null")
    @Size(max = 10, message = "Cannot be more than 10 characters")
    @NotEmpty(message = "You must supply a value for zipcode.")
    private String zipcode;

    //column name item_type
    //not null, 50 char
    @Column(name = "item_type")
    @NotNull(message= "itemType cannot be null")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    @NotEmpty(message = "You must supply a value for itemType.")
    private String itemType;

    //column name item_id
    //not null, links to game console or t_shirt id
    @Column(name = "item_id")
    @NotNull(message= "Item ID cannot be null")
    private int itemId;

    //column name unit_price
    //not null, 8 total, 2 after decimal
    @Column(name="unit_price")
    @NotNull(message= "Unit Price cannot be null")
    @Digits(integer = 6, fraction = 2, message = "Unit price has to no more than 8 digits long, and up 2 decimal places")
    private BigDecimal unitPrice;

    // column name quantity
    //not null
    @Column(name = "quantity")
    @NotNull(message= "Quantity cannot be null")
    private int quantity;

    //column name subtotal
    //not null, 8 total, 2 after decimal
    @Column(name = "subtotal")
    @NotNull(message= "Subtotal cannot be null")
    private BigDecimal subtotal;

    //not null, 8 total, 2 after decimal
    //based off rate from tax model
    @Column(name = "tax")
    @NotNull(message= "Tax cannot be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price cant be null and must be at least 0.01 cents")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Value must be less than {value}")
    private BigDecimal tax;

    //column name processing_fee
    //not null, 8 total, 2 after decimal
    @Column(name = "processing_fee")
    @NotNull(message= "Processing Fee cannot be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price cant be null and must be at least 0.01 cents")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Value must be less than {value}")
    private BigDecimal processingFee;

    //not null, 8 total, 2 after decimal
    @Column(name = "total")
    @NotNull(message= "Total cannot be null")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price cant be null and must be at least 0.01 cents")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Value must be less than {value}")
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
