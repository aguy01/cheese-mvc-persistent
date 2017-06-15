package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by shaikh on 4/11/2017.
 */

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30 )
    private String name;

    @ManyToMany
    private List<Cheese> cheeses = new ArrayList<>();

    @OneToMany /**owning side. each menu can have many images. and many images can be on 1 menu only. parent(menu) to
     children(images) relation.**/
    @JoinColumn(name = "menu_id")/* menu_id is the foreign key or connecting parent child of 2 different tables. Here
    1 class is interacting with another class.*/
    private List<Imagefile> imagefiles = new ArrayList<>();

    public Menu() {};

    public void addItem(Cheese item){cheeses.add(item);}


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    // accessors for List ImageFile


    public List<Imagefile> getImagefiles() {
        return imagefiles;
    }

    public void setImagefiles(List<Imagefile> imagefiles) {
        this.imagefiles = imagefiles;
    }
}
