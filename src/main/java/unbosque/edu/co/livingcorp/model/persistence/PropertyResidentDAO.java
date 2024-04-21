package unbosque.edu.co.livingcorp.model.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.PropertyResident;

import java.util.ArrayList;

public class PropertyResidentDAO implements InterfaceDAO<PropertyResident, Integer> {
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;

    @Override
    public PropertyResident save(PropertyResident entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public PropertyResident update(PropertyResident entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(PropertyResident entity) {
manager.remove(entity);
    }

    @Override
    public PropertyResident findById(Integer id) {
        return manager.find(PropertyResident.class, id);
    }

    @Override
    public ArrayList<PropertyResident> findAll() {
        TypedQuery<PropertyResident> query = manager.createQuery("SELECT e FROM PropertyResident e", PropertyResident.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
