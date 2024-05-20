package unbosque.edu.co.livingcorp.model.persistence;

import unbosque.edu.co.livingcorp.model.entity.Property;

public interface IPropertyDAO extends InterfaceDAO<Property, Integer >{

    Property findByName(String propertyName);



}
