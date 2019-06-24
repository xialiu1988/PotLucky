package com.hot2019pot.seattled41.xl.potluck.planner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class PotLuckUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String firstname;
    String lastname;
    String username;
    String password;
    @ManyToMany
    List<PotLuck> attendingPotlucks;
    @OneToMany(mappedBy="creator")
    List<PotLuck> createPotlucks;


    //constructor
    public PotLuckUser(){}
    public PotLuckUser(String firstname,String lastname,String username,String password){
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

    public List<PotLuck> getAttendingPotlucks() {
        return attendingPotlucks;
    }

    public List<PotLuck> getCreatePotlucks() {
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

    public void setAttendingPotlucks(List<PotLuck> attendingPotlucks) {
        this.attendingPotlucks = attendingPotlucks;
    }

    public void setCreatePotlucks(List<PotLuck> createPotlucks) {
        this.createPotlucks = createPotlucks;
    }
}
