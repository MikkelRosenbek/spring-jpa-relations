package ek.osnb.jpa.orders.model;

import ek.osnb.jpa.common.model.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;


@Entity
public class Customer extends BaseEntity {

    private String name;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Profile profile;

    public Customer(String name, Profile profile) {
        this.name = name;
        this.profile = profile;
    }

    public Customer(String name){
        this.name = name;
    }

    public Customer(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
