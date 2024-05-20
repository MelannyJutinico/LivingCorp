package unbosque.edu.co.livingcorp.model.persistence;

import java.util.ArrayList;

public interface InterfaceDAO <Type, Key>{

    Type save(Type entity);
    Type update(Type entity);
    void delete(Type entity);
    Type findById(Key id);
    ArrayList<Type> findAll();

}
