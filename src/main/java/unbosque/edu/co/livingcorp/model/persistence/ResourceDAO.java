package unbosque.edu.co.livingcorp.model.persistence;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.Resource;

import java.util.ArrayList;
@Stateless
public class ResourceDAO implements InterfaceDAO<Resource, Integer>{

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;


    @Override
    public Resource save(Resource entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public Resource update(Resource entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(Resource entity) {
        manager.remove(entity);
    }

    @Override
    public Resource findById(Integer id) {
        return manager.find(Resource.class, id);
    }

    @Override
    public ArrayList<Resource> findAll() {
        TypedQuery<Resource> query = manager.createQuery("SELECT e FROM Resource e", Resource.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
