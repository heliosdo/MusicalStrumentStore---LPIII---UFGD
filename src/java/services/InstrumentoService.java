package services;

import entities.Instrumento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class InstrumentoService extends EntityService<Instrumento> {

    @PersistenceContext(unitName = "MusicShopORDS")
    private EntityManager em;


    public InstrumentoService() {
        super(Instrumento.class);
    }
    
    public List<Instrumento> filter(String prefix){
        prefix += "%";
        Query query = em.createQuery("SELECT instrumento from Instrumento instrumento WHERE instrumento.modelo LIKE :prefix");
        query.setParameter("prefix", prefix);
        return query.getResultList();
    }
    
}
