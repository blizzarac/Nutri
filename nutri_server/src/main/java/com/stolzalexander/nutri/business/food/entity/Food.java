package com.stolzalexander.nutri.business.food.entity;

import com.stolzalexander.nutri.business.nutrient.entity.Nutrient;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexanderstolz
 */
@Entity
@NamedQuery(name = Food.findAll, query = "select f from Food f")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Food {

    static final String PREFIX = "food.entity.Food";
    public static final String findAll = PREFIX + "findAll";

    @Id
    @GeneratedValue
    private long id;
    private String caption;
    private String description;
    
    @OneToMany
    private List<Nutrient> nutrients;

    public Food(String caption, String description) {
        this.caption = caption;
        this.description = description;
        this.nutrients = new ArrayList<>();
    }
    
    public Food() {
    }
    
    public long getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
