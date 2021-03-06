package com.plotva.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "meals", uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id", "name"}, name = "meal_unique_name_idx"))
public class Meal extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @Nullable
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 1000)
    private int price;

    @Column(name = "date", nullable = false, columnDefinition = "DATE DEFAULT now()")
    @NotNull
    private LocalDate date;

    public Meal() {
    }

    public Meal(Meal meal) {
        this(meal.getId(), meal.getName(), meal.getRestaurant(), meal.getPrice(), meal.getDate());
    }

    public Meal(Integer id, String name, Restaurant restaurant, int price, LocalDate date) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "  id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
