package gr.kariera.MindTheCode.SecondProject.SecondProject.Entities;


import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private BigDecimal price;

    private int stock;

    private String picture;

    @OneToMany(mappedBy="product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> categories;

    public boolean hasStock(int amount){
        return (this.getStock()>0)&& (amount<=this.getStock());
    }

    public void decreaseStock(int amount) {
        this.stock -= amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
