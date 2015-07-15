package org.lunding.deliciousclock.data;

import java.io.Serializable;

/**
 * Created by Lunding on 13/07/15.
 */
public class Address implements Serializable{

    private String address;
    private String city;
    private String state;
    private String zipCode;

    public Address(String address, String city, String state, String zipCode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }
}
