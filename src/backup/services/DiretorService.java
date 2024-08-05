/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Diretor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristian
 */
@Stateless
public class DiretorService extends EntityService<Diretor> {

    @PersistenceContext(unitName = "ClubeCinemaORPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiretorService() {
        super(Diretor.class);
    }
    
    public List<Diretor> filter(String prefix){
        prefix += "%";
        Query query = em.createQuery("SELECT diretor from Diretor diretor WHERE diretor.nome LIKE :prefix");
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }
}
