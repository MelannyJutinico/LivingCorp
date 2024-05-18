package unbosque.edu.co.livingcorp.model.persistence;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.ResourceBooking;

import java.util.ArrayList;
@Stateless
public class ResourceBookingDAO implements InterfaceDAO<ResourceBooking, Integer>{

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;


    @Override
    public ResourceBooking save(ResourceBooking entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public ResourceBooking update(ResourceBooking entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(ResourceBooking entity) {
        if (!manager.contains(entity)) {
            entity = manager.merge(entity);
        }
        manager.remove(entity);
    }

    @Override
    public ResourceBooking findById(Integer id) {
        return manager.find(ResourceBooking.class, id);
    }

    @Override
    public ArrayList<ResourceBooking> findAll() {
        TypedQuery<ResourceBooking> query = manager.createQuery("SELECT e FROM ResourceBooking e", ResourceBooking.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
