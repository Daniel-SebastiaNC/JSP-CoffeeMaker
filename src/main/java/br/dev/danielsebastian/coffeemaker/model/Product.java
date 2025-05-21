package br.dev.danielsebastian.coffeemaker.model;

import java.time.LocalDate;

public class Product {

    private Long id;
    private String name;
    private double value;
    private int quantity;
    private LocalDate dateCreated;
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, Double value, Integer quantity, LocalDate dateCreated) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.quantity = quantity;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", quantity=" + quantity +
                ", dateCreated=" + dateCreated +
                ", category=" + category +
                '}';
    }
}
