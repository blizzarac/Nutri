package com.stolzalexander.nutri.business.food.boundary;

import com.stolzalexander.nutri.business.food.entity.Food;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alexanderstolz
 */
@Stateless
public class FoodManager {

    @PersistenceContext
    EntityManager em;
    
    
    public Food findById(long id) {
        return this.em.find(Food.class, id);
    }

    public void delete(long id) {
        try {
            Food reference = this.em.getReference(Food.class, id);
            this.em.remove(reference);
        } catch (EntityNotFoundException e) {
            // We want to remove it...
        }
    }

    public Food save(Food todo) {
        return this.em.merge(todo);
    }

    public List<Food> all() {
        return this.em.createNamedQuery(Food.findAll, Food.class).getResultList();
    }
}
