package unbosque.edu.co.livingcorp.model.persistence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.ServiceRequest;

import java.util.ArrayList;

public class ServiceRequestDAO implements InterfaceDAO<ServiceRequest, Integer>{

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;


    @Override
    public ServiceRequest save(ServiceRequest entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public ServiceRequest update(ServiceRequest entity) {
        manager.merge(entity);
        return entity;
    }

    @Override
    public void delete(ServiceRequest entity) {
        manager.remove(entity);
    }

    @Override
    public ServiceRequest findById(Integer id) {
        return manager.find(ServiceRequest.class, id);
    }

    @Override
    public ArrayList<ServiceRequest> findAll() {
        TypedQuery<ServiceRequest> query = manager.createQuery("SELECT e FROM ServiceRequest e", ServiceRequest.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
