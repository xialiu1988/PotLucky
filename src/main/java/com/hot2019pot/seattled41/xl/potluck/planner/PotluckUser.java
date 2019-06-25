package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    List<Potluck> attendingPotlucks;

    @OneToMany(mappedBy="creator")
    List<Potluck> createPotlucks;

    @OneToMany
    Set<PotluckItem> potluckItems;

    //constructor
    public PotluckUser(){}
    public PotluckUser(String firstname, String lastname, String username, String password){
        this.firstname=firstname;
        this.lastname=lastname;
        this.username=username;
        this.password=password;
    }


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

    public List<Potluck> getAttendingPotlucks() {
        return attendingPotlucks;
    }

    public List<Potluck> getCreatePotlucks() {
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

    public void setAttendingPotlucks(List<Potluck> attendingPotlucks) {
        this.attendingPotlucks = attendingPotlucks;
    }

    public void setCreatePotlucks(List<Potluck> createPotlucks) {
        this.createPotlucks = createPotlucks;
    }
}
