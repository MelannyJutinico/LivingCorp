package unbosque.edu.co.livingcorp.model.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.PropertyResource;

import java.util.ArrayList;

public class PropertyResourceDAO implements InterfaceDAO <PropertyResource, Integer>{

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;
    @Override
    public PropertyResource save(PropertyResource entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public PropertyResource update(PropertyResource entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(PropertyResource entity) {
        manager.remove(entity);
    }

    @Override
    public PropertyResource findById(Integer id) {
        return manager.find(PropertyResource.class, id);
    }

    @Override
    public ArrayList<PropertyResource> findAll() {
        TypedQuery<PropertyResource> query = manager.createQuery("SELECT e FROM PropertyResource e", PropertyResource.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
