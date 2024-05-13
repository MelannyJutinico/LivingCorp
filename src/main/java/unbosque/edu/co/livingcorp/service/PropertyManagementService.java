package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.model.entity.Property;
import unbosque.edu.co.livingcorp.model.entity.WebUser;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import unbosque.edu.co.livingcorp.model.persistence.PropertyDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PropertyManagementService implements Serializable {

    @Inject
    private InterfaceDAO<Property, Integer> propertyDAO;
    @Inject InterfaceDAO<PropertyResource, Integer> propertyResourceDAO;

    @Inject
    private InterfaceDAO <WebUser, String> userDAO;

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



    public PropertyDTO findPropertyById(Integer id){
        return modelMapper.map(propertyDAO.findById(id), PropertyDTO.class);
    }

    public List<PropertyResourceDTO> getPropertyResources(Integer id){
        int pId = propertyDAO.findById(id).getPropertyId();
        var propertyResources = propertyResourceDAO.findAll();
        List<PropertyResourceDTO> propertyResourceDTOs = new ArrayList<>();
        for(PropertyResource propertyResource : propertyResources){
            if(propertyResource.getProperty().getPropertyId() == pId){
                propertyResourceDTOs.add(modelMapper.map(propertyResource, PropertyResourceDTO.class));

            }
        }
        return propertyResourceDTOs;
    }


    public PropertyResourceDTO getPropertyResourceById(Integer id){
        return modelMapper.map(propertyResourceDAO.findById(id), PropertyResourceDTO.class);
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
