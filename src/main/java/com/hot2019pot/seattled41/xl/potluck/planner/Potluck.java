package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Potluck object.
 * Event.
 */

// Could the eventname etc. be made private since there are getters and setters

@Entity
public class Potluck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String eventname;
    String details;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date dateofPotluck;
    String location;
    //who create the potluck
    @ManyToOne
    PotluckUser creator;

    String code;
    //get lat and lng from api and store int the array
    double[] mapResult;
    @ManyToMany
    Set<PotluckUser> attendees;
    //what are the options for FetchType other than EAGER
    @OneToMany(fetch=FetchType.EAGER, mappedBy="potluck")
     List<PotluckItem> potluckItemList;

    /**
     * Default constructor
     */
    public Potluck() {}

    /**
     * Constructor with params
     * @param eventname String, Potluck name
     * @param dateofPotluck Date, Potluck date
     * @param location String, Potluck location
     * @param details String, Potluck additional details
     * @param creator PotluckUser, user who created Potluck
     * @param code String, code to share with others
     *             so other users could see and add to your Potluck
     */
    public Potluck(String eventname, Date dateofPotluck, String location, String details, PotluckUser creator, String code) {
        this.eventname = eventname;
        this.dateofPotluck = dateofPotluck;
        this.location = location;
        this.details = details;
        this.creator=creator;
        this.code=code;
    }

    /** Getter sand Setters **/
    public String getCode(){return code;}

    public void setCode(String code){this.code=code;}

    public void setId(long id) {
        this.id = id;
    }

    public List<PotluckItem> getPotluckItemList() {
        return potluckItemList;
    }

    public void setPotluckItemList(List<PotluckItem> potluckItemList) {
        this.potluckItemList = potluckItemList;
    }
    public double[] getMapResult(){
        return this.mapResult;
    }

    public void setMapResult(double[] mapResult){
        this.mapResult=mapResult;
    }
    public long getId() {
        return id;
    }

    public String getEventname() {
        return eventname;
    }

    public Date getDateofPotluck() {
        return dateofPotluck;
    }

    public String getLocation() {
        return location;
    }

    public PotluckUser getCreator() {
        return creator;
    }

    public Set<PotluckUser> getAttendees() {
        return attendees;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setDateofPotluck(Date dateofPotluck) {
        this.dateofPotluck = dateofPotluck;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCreator(PotluckUser creator) {
        this.creator = creator;
    }

    public void setAttendees(Set<PotluckUser> attendees) {
        this.attendees = attendees;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
