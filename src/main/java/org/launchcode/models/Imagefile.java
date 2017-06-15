package org.launchcode.models;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 * Created by shaikh on 5/4/2017.
 */

@Entity //this class or object will be stored in the mysql db with that annotation.
/* this annotation will tell the spring boot to work with the Java API to store this object in the relational db
* with the fileds below as columns for this class, unless the fields are exempt to appear in the relational db.**/
public class Imagefile {

    @Id
    @GeneratedValue
    private int id;

    private String filename;

    @Lob
    private byte[] imagecontent;



    @ManyToOne/**owning side. each menu can have many images,  conversely many images can be on 1 menu only. child to parent relation.
     non-owning or inverse side.***/
    private Menu menu;

    //constructor
    public Imagefile(String filename, byte[] imagecontent) {
        this.filename = filename;
        this.imagecontent = imagecontent;
    }
    public Imagefile() {}//empty or default constructor, which hibernate will use it. all relaitonal object classes have
    //empty default constructor.

    //Getters and Setters - Accessors.

    public int getId() {
        return id;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getImagecontent() {
        return imagecontent;
    }

    public void setImagecontent(byte[] imagecontent) {
        this.imagecontent = imagecontent;
    }



    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }



}
