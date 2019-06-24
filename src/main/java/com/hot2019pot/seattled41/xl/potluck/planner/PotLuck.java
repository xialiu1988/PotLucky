package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class PotLuck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String eventname;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    Date dateofPotluck;

    String location;
    //who create the potluck
    @ManyToOne
    PotLuckUser creator;
    @Column
    @ElementCollection(targetClass=String.class)
    List<String> stuff;
    String code;
    @ManyToMany
    List<PotLuckUser> attendees;

    public PotLuck() {
    }

    public PotLuck(String eventname, Date dateofPotluck, String location,PotLuckUser creator,String code) {
        this.eventname = eventname;
        this.dateofPotluck = dateofPotluck;
        this.location = location;
        this.creator=creator;
        this.code=code;
//        this.stuff=new ArrayList<>();
    }
  public String getCode(){return code;}
  public void setCode(String code){this.code=code;}

    public List<String> getStuff(){return stuff;}
    public void setStuff(List<String> stuff){this.stuff=stuff;}


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

    public PotLuckUser getCreator() {
        return creator;
    }

    public List<PotLuckUser> getAttendees() {
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

    public void setCreator(PotLuckUser creator) {
        this.creator = creator;
    }

    public void setAttendees(List<PotLuckUser> attendees) {
        this.attendees = attendees;
    }
}
