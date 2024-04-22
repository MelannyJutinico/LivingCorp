package unbosque.edu.co.livingcorp.model.persistence;

import jakarta.persistence.PersistenceContext;
import unbosque.edu.co.livingcorp.model.entity.ServiceRFQ;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;

public class ServicesRfqDAO implements InterfaceDAO<ServiceRFQ, Integer>{

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;

    @Override
    public ServiceRFQ save(ServiceRFQ entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public ServiceRFQ update(ServiceRFQ entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(ServiceRFQ entity) {
        manager.remove(entity);
    }

    @Override
    public ServiceRFQ findById(Integer id) {
        return manager.find(ServiceRFQ.class, id);
    }

    @Override
    public ArrayList<ServiceRFQ> findAll() {
        TypedQuery<ServiceRFQ> query = manager.createQuery("SELECT e FROM ServiceRFQ e", ServiceRFQ.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
