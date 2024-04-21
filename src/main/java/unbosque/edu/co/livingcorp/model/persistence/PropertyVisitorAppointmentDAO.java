package unbosque.edu.co.livingcorp.model.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import unbosque.edu.co.livingcorp.model.entity.PropertyVisitorAppointment;

import java.util.ArrayList;

public class PropertyVisitorAppointmentDAO implements  InterfaceDAO<PropertyVisitorAppointment, Integer>{
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;


    @Override
    public PropertyVisitorAppointment save(PropertyVisitorAppointment entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public PropertyVisitorAppointment update(PropertyVisitorAppointment entity) {
        manager.merge(entity);
        return entity ;
    }

    @Override
    public void delete(PropertyVisitorAppointment entity) {
    manager.remove(entity);
    }

    @Override
    public PropertyVisitorAppointment findById(Integer id) {
        return manager.find(PropertyVisitorAppointment.class, id);
    }

    @Override
    public ArrayList<PropertyVisitorAppointment> findAll() {
        TypedQuery<PropertyVisitorAppointment> query = manager.createQuery("SELECT e FROM PropertyVisitorAppointment e", PropertyVisitorAppointment.class);
        ArrayList list = (ArrayList) query.getResultList();
        return list;
    }
}
