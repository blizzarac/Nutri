package com.stolzalexander.nutri.business.nutrient.boundary;

import com.stolzalexander.nutri.business.nutrient.entity.Nutrient;
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
public class NutrientManager {

    @PersistenceContext
    EntityManager em;
    
    
    public Nutrient findById(long id) {
        return this.em.find(Nutrient.class, id);
    }

    public void delete(long id) {
        try {
            Nutrient reference = this.em.getReference(Nutrient.class, id);
            this.em.remove(reference);
        } catch (EntityNotFoundException e) {
            // We want to remove it...
        }
    }

    public Nutrient save(Nutrient todo) {
        return this.em.merge(todo);
    }

    public List<Nutrient> all() {
        return this.em.createNamedQuery(Nutrient.findAll, Nutrient.class).getResultList();
    }
}
