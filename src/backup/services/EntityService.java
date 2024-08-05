/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.PersistentEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Cristian
 */
public abstract class EntityService<T extends PersistentEntity> {
    @PersistenceContext(unitName = "ClubeCinemaORPU")
    protected EntityManager em;
    
    private Class<T> entityClass;

    public EntityService(Class<T> entityClass) { this.entityClass = entityClass;}

    protected abstract EntityManager getEntityManager();

    public void create(T entity) { getEntityManager().persist(entity);}

    public void edit(T entity) { getEntityManager().merge(entity);}

    public void remove(T entity) {getEntityManager().remove(getEntityManager().merge(entity));}

    public T find(Object id) {return getEntityManager().find(entityClass, id);}
    
    public List<T> getAll(){
        CriteriaQuery query = em.getCriteriaBuilder().createQuery();
        query.select(query.from(entityClass));
        return em.createQuery(query).getResultList();
    }
    
    public long getCount(){
        CriteriaQuery query = em.getCriteriaBuilder().createQuery();
        Root<T> root = query.from(entityClass);
        query.select(em.getCriteriaBuilder().count(root));
        return(Long) em.createQuery(query).getSingleResult();
    }
    
    protected T attach(T entity){ return find(entity.getId());}
    
}
