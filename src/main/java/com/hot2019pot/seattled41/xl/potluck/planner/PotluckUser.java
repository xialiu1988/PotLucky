package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * PotluckUser object - user.
 */
@Entity
public class PotluckUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String firstname;
    String lastname;

    @Column(unique = true)
    String username;
    String password;

    @ManyToMany(mappedBy = "attendees",cascade = CascadeType.ALL)
    Set<Potluck> attendingPotlucks;

    @OneToMany(mappedBy="creator")
    Set<Potluck> createPotlucks;

    /**
     * Default constructor
     */
    public PotluckUser(){}

    /**
     * Constructor with params
     * @param firstname String, user first name
     * @param lastname String, user last name
     * @param username String, user username - must be unique
     * @param password String, user password
     */
    public PotluckUser(String firstname, String lastname, String username, String password){
        this.firstname=firstname;
        this.lastname=lastname;
        this.username=username;
        this.password=password;
    }


    /* Getters and Setters */
    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public Set<Potluck> getAttendingPotlucks() {
        return attendingPotlucks;
    }

    public Set<Potluck> getCreatePotlucks() {
        return createPotlucks;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAttendingPotlucks(Set<Potluck> attendingPotlucks) {
        this.attendingPotlucks = attendingPotlucks;
    }

    public void setCreatePotlucks(Set<Potluck> createPotlucks) {
        this.createPotlucks = createPotlucks;
    }
}
