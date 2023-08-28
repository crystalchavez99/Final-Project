package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

//tale name console
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console {
    //column name console_id
    //primary key auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "console_id")
    private int id;

    //not null, 50 char
    private String model;

    //not null, 50 char
    private String manufacturer;

    //column name memory_amount
    //20 char
    private String memoryAmount;

    //20 char
    private String processor;

    //not null, 5 total 2 after decimal
    private BigDecimal price;

    //not null
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
        Console console = (Console) o;
        return id == console.id && quantity == console.quantity && model.equals(console.model) && manufacturer.equals(console.manufacturer) && Objects.equals(memoryAmount, console.memoryAmount) && Objects.equals(processor, console.processor) && price.equals(console.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, manufacturer, memoryAmount, processor, price, quantity);
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
