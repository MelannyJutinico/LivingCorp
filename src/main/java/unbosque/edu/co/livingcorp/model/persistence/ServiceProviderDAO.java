package unbosque.edu.co.livingcorp.model.persistence;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.ServiceProvider;

import java.util.ArrayList;
@Stateless
public class ServiceProviderDAO implements InterfaceDAO<ServiceProvider, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;

    @Override
    public ServiceProvider save(ServiceProvider entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public ServiceProvider update(ServiceProvider entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(ServiceProvider entity) {
        manager.remove(entity);
    }

    @Override
    public ServiceProvider findById(Integer id) {
        return manager.find(ServiceProvider.class, id);

    }

    @Override
    public ArrayList<ServiceProvider> findAll() {
        TypedQuery<ServiceProvider> query = manager.createQuery("SELECT e FROM ServiceProvider e", ServiceProvider.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
