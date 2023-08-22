package com.company.gamestore.model;

import java.math.BigDecimal;
import java.util.Objects;

//table name t_shirt
public class TShirt {
    //column name tshirt_id
    //primary key auto increment
    private int id;

    //not null, 20 char
    private String size;

    //not null, 20 char
    private String color;

    //not null, 255 char
    private String description;

    //not null, 5 total 2 after decimal
    private String price;

    //not null
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
        TShirt tShirt = (TShirt) o;
        return id == tShirt.id && quantity == tShirt.quantity && size.equals(tShirt.size) && color.equals(tShirt.color) && description.equals(tShirt.description) && price.equals(tShirt.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, size, color, description, price, quantity);
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
