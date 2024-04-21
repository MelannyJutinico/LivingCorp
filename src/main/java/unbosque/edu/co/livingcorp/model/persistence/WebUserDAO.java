package unbosque.edu.co.livingcorp.model.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import unbosque.edu.co.livingcorp.model.entity.WebUser;

import java.util.ArrayList;

public class WebUserDAO implements InterfaceDAO <WebUser, String>{

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager manager;


    @Override
    public WebUser save(WebUser entity) {
        manager.persist(entity);
        return entity;
    }

    @Override
    public WebUser update(WebUser entity) {
        return manager.merge(entity);
    }

    @Override
    public void delete(WebUser entity) {
        manager.remove(entity);
    }

    @Override
    public WebUser findById(String id) {
        return manager.find(WebUser.class, id);
    }

    @Override
    public ArrayList<WebUser> findAll() {
        return null;
    }
}
