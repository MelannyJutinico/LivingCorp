package unbosque.edu.co.livingcorp.model.persistence;

import java.util.ArrayList;

public interface InterfaceDAO <Type, Key>{

    public Type save(Type entity);
    public Type update(Type entity);
    public void delete(Type entity);
    public Type findById(Key id);
    public ArrayList<Type> findAll();

}
