package services;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EntityFinder implements Serializable {
    
    @PersistenceContext(unitName = "MusicShopORDS")
    private EntityManager em;

    public Object find(Class<?> type, Object id) {
        return em.find(type, id);
    }
}

