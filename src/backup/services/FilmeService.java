/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Filme;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cristian
 */
@Stateless
public class FilmeService extends EntityService<Filme> {

    @PersistenceContext(unitName = "ClubeCinemaORPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FilmeService() {
        super(Filme.class);
    }
    
}
