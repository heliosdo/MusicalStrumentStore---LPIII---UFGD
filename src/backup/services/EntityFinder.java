/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cristian
 */
@Stateless
public class EntityFinder {
    @PersistenceContext(unitName = "ClubeCinemaORPU")
    private EntityManager em;
    
    public Object find(Class type, Object id){ return em.find(type, id);}
    
}
