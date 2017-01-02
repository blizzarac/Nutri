package com.stolzalexander.nutri.business.nutrient.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexanderstolz
 */
@Entity
@NamedQuery(name = Nutrient.findAll, query = "select n from Nutrient n")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Nutrient {

    static final String PREFIX = "nutrient.entity.Nutrient";
    public static final String findAll = PREFIX + "findAll";
    
    @Id
    @GeneratedValue
    private long id;
    private String caption;
    private String description;
    

    public Nutrient(String caption, String description) {
        this.caption = caption;
        this.description = description;
    }
    
    public Nutrient() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
