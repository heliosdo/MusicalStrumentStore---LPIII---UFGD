/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Ator;
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
public class AtorService extends EntityService<Ator> {

    @PersistenceContext(unitName = "ClubeCinemaORPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AtorService() {
        super(Ator.class);
    }

    public List<Ator> filter(String prefix) {
        prefix += "%";
        Query query = em.createQuery("SELECT ator from Ator ator WHERE ator.nome LIKE :prefix");
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }

}
