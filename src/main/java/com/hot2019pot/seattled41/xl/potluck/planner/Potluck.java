package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Potluck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String eventname;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date dateofPotluck;

    String location;
    //who create the potluck
    @ManyToOne
    PotluckUser creator;
//    @Column
//    @ElementCollection(targetClass=String.class)
//    List<String> stuff;
    String code;
    @ManyToMany
    Set<PotluckUser> attendees;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="potluck")
    List<PotluckItem> potluckItemList;

    public Potluck() {
    }

    public Potluck(String eventname, Date dateofPotluck, String location, PotluckUser creator, String code) {
        this.eventname = eventname;
        this.dateofPotluck = dateofPotluck;
        this.location = location;
        this.creator=creator;
        this.code=code;
//        this.potluckItemList=new ArrayList<>();
    }
    public String getCode(){return code;}
    public void setCode(String code){this.code=code;}

//    public List<String> getStuff(){return stuff;}
//    public void setStuff(List<String> stuff){this.stuff=stuff;}


    public void setId(long id) {
        this.id = id;
    }

    public List<PotluckItem> getPotluckItemList() {
        return potluckItemList;
    }

    public void setPotluckItemList(List<PotluckItem> potluckItemList) {
        this.potluckItemList = potluckItemList;
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
}