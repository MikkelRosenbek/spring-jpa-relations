package ek.osnb.jpa.orders.model;

import ek.osnb.jpa.common.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity
public class Profile extends BaseEntity {
    private String bio;

    @OneToOne
    private Customer customer;


    public Profile(String bio, Customer customer) {
        this.bio = bio;
        this.customer = customer;
    }

    public Profile(){

    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
