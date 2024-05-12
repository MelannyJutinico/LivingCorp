package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.entity.Property;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.io.Serializable;
import java.util.ArrayList;

@Stateless
public class PropertyManagementService implements Serializable {

    @Inject
    private InterfaceDAO<Property, Integer> propertyDAO;

    private final ModelMapper modelMapper = new ModelMapper();

    public ArrayList<PropertyDTO> listProperties(){

        ArrayList<Property> properties = propertyDAO.findAll();
        ArrayList<PropertyDTO> propertyDTOs = new ArrayList<>();

        for (Property property : properties) {
            propertyDTOs.add(modelMapper.map(property, PropertyDTO.class));
        }
        return propertyDTOs;
    }

    public ArrayList<String> getCities(){
        ArrayList<String> cities = new ArrayList<>();
        for (Property property : propertyDAO.findAll()) {
            if(!cities.contains(property.getPropertyCity())){
                cities.add(property.getPropertyCity());
            }
        }
        return cities;
    }

    public ArrayList<String> getNameProperties(){
        ArrayList<String> names = new ArrayList<>();
        for(Property property : propertyDAO.findAll()){
            if(!names.contains(property.getPropertyName())){
                names.add(property.getPropertyName());
            }
        }
        return names;
    }

    public PropertyDTO updatePropertyPurchase(PropertyDTO propertyDTO){
        Property property = propertyDAO.findById(propertyDTO.getPropertyId());
        property.setAvailableForSale(false);
        return modelMapper.map(propertyDAO.update(property), PropertyDTO.class);
    }

    public PropertyDTO updatePropertyRent(PropertyDTO propertyDTO){
        Property property = propertyDAO.findById(propertyDTO.getPropertyId());
        property.setAvailableForRent(false);
        return modelMapper.map(propertyDAO.update(property), PropertyDTO.class);
    }
}
