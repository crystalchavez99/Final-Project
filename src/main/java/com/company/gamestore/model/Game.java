package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game {
    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    @NotEmpty(message = "You must supply a value for title.")
    private String title;

    @Column(name = "esrb_rating")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    @NotEmpty(message = "You must supply a value for esrbRating.")
    private String esrbRating;

    @Column(name = "description")
    @Size(max = 255, message = "Cannot be more than 255 characters")
    @NotEmpty(message = "You must supply a value for description.")
    private String description;

    @Column(name = "price")
    @NotNull(message= "price cannot be null")
    @Digits(integer = 3, fraction = 2, message = "price has to no more than 5 digits long, and up 2 decimal places")
    private BigDecimal price;

    @Column(name = "studio")
    @Size(max = 50, message = "Cannot be more than 50 characters")
    @NotEmpty(message = "You must supply a value for studio.")
    private String studio;

    @Column(name = "quantity")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
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
        Game game = (Game) o;
        return id == game.id && quantity == game.quantity && title.equals(game.title) && esrbRating.equals(game.esrbRating) && description.equals(game.description) && price.equals(game.price) && studio.equals(game.studio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, esrbRating, description, price, studio, quantity);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
