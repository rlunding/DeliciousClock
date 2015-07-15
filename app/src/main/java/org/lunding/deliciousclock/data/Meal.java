package org.lunding.deliciousclock.data;

import java.io.Serializable;

/**
 * Created by Lunding on 13/07/15.
 */
public class Meal implements Serializable{

    private int id;
    private int image;
    private int imagecard;
    private int homecard;
    private String title;
    private String description;



    public Meal(int id, int image, int imagecard, int homecard, String title, String description) {

        this.id = id;
        this.image = image;
        this.imagecard = imagecard;
        this.homecard = homecard;
        this.title = title;
        this.description = description;
    }
    public int getImagecard() {
        return imagecard;
    }

    public int getHomecard() {
        return homecard;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Meal:" + id + ", " + title;
    }
}
