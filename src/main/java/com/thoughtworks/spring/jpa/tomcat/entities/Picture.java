package com.thoughtworks.spring.jpa.tomcat.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qnxu on 1/27/15.
 */
@Entity
@Table(name = "picture")
public class Picture implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="user_seq_gen")
    @SequenceGenerator(name="user_seq_gen", sequenceName="USER_ID_GENERATOR")
    private Long id;
    private String name;
    private String description;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getPictureName(){
        return name;
    }
    public void setPictureName(String pictureName){
        this.name = pictureName;
    }
    public String getPictureDescription(){
        return description;
    }
    public void setPictureDescription(String pictureDescription){
        this.description = pictureDescription;
    }
//    public Map<String, String> getPictureInformation(){
//        Map<String, String> pictureInformation = new HashMap<>();
//        pictureInformation.put("name", name);
//        pictureInformation.put("description", description);
//
//        return pictureInformation;
//    }
}
