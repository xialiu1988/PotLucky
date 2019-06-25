package com.hot2019pot.seattled41.xl.potluck.planner;


import javax.persistence.*;

@Entity
public class PotluckItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String item;
    int quantity;
    @OneToOne
    PotluckUser user;
    @ManyToOne
    Potluck potluck;

    public PotluckItem() {}

    public PotluckItem(String item, int quantity, Potluck potLuck, PotluckUser user) {
        this.item = item;
        this.quantity = quantity;
        this.potluck = potLuck;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PotluckUser getUser() {
        return user;
    }

    public void setUser(PotluckUser user) {
        this.user = user;
    }
}
