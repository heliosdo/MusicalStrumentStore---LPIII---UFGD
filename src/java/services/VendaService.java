package services;

import entities.Venda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class VendaService extends EntityService<Venda> {

    @PersistenceContext(unitName = "MusicShopORDS")
    private EntityManager em;


    public VendaService() {
        super(Venda.class);
    }
    
}
