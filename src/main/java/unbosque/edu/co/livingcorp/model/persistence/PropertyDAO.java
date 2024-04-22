package unbosque.edu.co.livingcorp.model.persistence;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.Property;

import java.util.ArrayList;

public class PropertyDAO implements InterfaceDAO<Property, Integer>{

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;

    @Override
    public Property save(Property entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public Property update(Property entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(Property entity) {
        manager.remove(entity);
    }

    @Override
    public Property findById(Integer id) {
        return manager.find(Property.class, id);
    }

    @Override
    public ArrayList<Property> findAll() {
        TypedQuery<Property> query = manager.createQuery("SELECT e FROM Property e", Property.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
