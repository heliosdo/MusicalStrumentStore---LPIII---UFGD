package services;

import entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class ClienteService extends EntityService<Cliente> {

    @PersistenceContext(unitName = "MusicShopORDS")
    private EntityManager em;


    public ClienteService() {
        super(Cliente.class);
    }
    
   
    public List<Cliente> filter(String prefix) {
        prefix += "%";
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :prefix");
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }
    
}
