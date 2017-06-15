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


