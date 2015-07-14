package org.lunding.deliciousclock.register;

import org.lunding.deliciousclock.data.Address;
import org.lunding.deliciousclock.data.Meal;
import org.lunding.deliciousclock.data.Time;

import java.io.Serializable;

/**
 * Created by Lunding on 13/07/15.
 */
public class SignupObject implements Serializable{

    public static final String SIGNOP_OBJECT_TAG = "SIGNOP_OBJECT_TAG";

    private Meal meal;
    private Time time;
    private Address address;

    public SignupObject() {
        //TODO: set default!
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
