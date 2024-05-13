package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResourceDTO;
import unbosque.edu.co.livingcorp.model.entity.Property;
import unbosque.edu.co.livingcorp.model.entity.PropertyResource;
import unbosque.edu.co.livingcorp.model.entity.WebUser;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class PropertyManagementService implements Serializable {

    @Inject
    private InterfaceDAO<Property, Integer> propertyDAO;

    @Inject
    private InterfaceDAO<PropertyResource, Integer> propertyResourceDAO;

    @Inject
    private InterfaceDAO <WebUser, String> userDAO;

    private final ModelMapper modelMapper = new ModelMapper();

    private static final Logger logger = LogManager.getLogger(PropertyManagementService.class);


    public ArrayList<PropertyDTO> listProperties(){
        logger.info("Listando propiedades...");
        ArrayList<Property> properties = propertyDAO.findAll();
        ArrayList<PropertyDTO> propertyDTOs = new ArrayList<>();

        for (Property property : properties) {
            propertyDTOs.add(modelMapper.map(property, PropertyDTO.class));
        }
        return propertyDTOs;
    }

    public ArrayList<String> getCities(){
        logger.info("Obteniendo ciudades...");
        ArrayList<String> cities = new ArrayList<>();
        for (Property property : propertyDAO.findAll()) {
            if(!cities.contains(property.getPropertyCity())){
                cities.add(property.getPropertyCity());
            }
        }
        return cities;
    }

    public ArrayList<String> getNameProperties(){
        logger.info("Obteniendo nombres de las propiedades...");
        ArrayList<String> names = new ArrayList<>();
        for(Property property : propertyDAO.findAll()){
            if(!names.contains(property.getPropertyName())){
                names.add(property.getPropertyName());
            }
        }
        return names;
    }



    public PropertyDTO findPropertyById(Integer id){
        logger.info("Obteniendo propiedad de id: "+id.toString());
        return modelMapper.map(propertyDAO.findById(id), PropertyDTO.class);
    }

    public List<PropertyResourceDTO> getPropertyResources(Integer id){
        int pId = propertyDAO.findById(id).getPropertyId();
        var propertyResources = propertyResourceDAO.findAll();
        logger.info("Listando recursos de propiedad con id: "+id.toString());
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



}
