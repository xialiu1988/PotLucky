package com.hot2019pot.seattled41.xl.potluck.planner;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * PotluckItem object
 * that is attached to Potluck object.
 */
@Entity
public class PotluckItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String item;
    int quantity;
    @ManyToOne
    PotluckUser user;
    @ManyToOne
    Potluck potluck;

    /**
     * Default Constructor
     */
    public PotluckItem() {}

    /**
     * Constructor with params
     * @param item String, item
     * @param quantity int, quantity of item
     * @param potluck Potluck object, event PotluckItem object is part of
     * @param user PotluckUser, the user who created and
     *             is responsible for newly created PotLuckItem
     */
    public PotluckItem(String item, int quantity,Potluck potluck, PotluckUser user) {
        this.item = item;
        this.quantity = quantity;
        this.potluck=potluck;
        this.user = user;
    }

    /** Getters and Setters **/
    public Potluck getPotluck(){
        return potluck;
    }
    public void setPotluck(Potluck potluck){
        this.potluck=potluck;
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
