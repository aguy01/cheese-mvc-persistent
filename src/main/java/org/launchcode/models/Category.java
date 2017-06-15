package org.launchcode.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaikh on 4/10/2017.
 */


@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")/*foreign key(a child) in cheese table. which corresponds to the primary key
    in the Category table. */
    private List<Cheese> cheeses = new ArrayList<>(); /****/


    public Category() {}

    public Category(String name) { this.name = name; }


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
}
