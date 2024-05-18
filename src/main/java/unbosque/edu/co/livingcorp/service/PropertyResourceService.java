package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.PropertyResourceExistingException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResourceDTO;
import unbosque.edu.co.livingcorp.model.dto.ResourceDTO;
import unbosque.edu.co.livingcorp.model.entity.Property;
import unbosque.edu.co.livingcorp.model.entity.PropertyResource;
import unbosque.edu.co.livingcorp.model.entity.Resource;
import unbosque.edu.co.livingcorp.model.entity.WebUser;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class PropertyResourceService implements Serializable {

    @Inject
    private InterfaceDAO<Property, Integer> propertyDAO;

    @Inject
    private InterfaceDAO<PropertyResource, Integer> propertyResourceDAO;

    private final ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger = LogManager.getLogger(PropertyResourceService.class);

    public void createPropertyResource(PropertyResourceDTO propertyResourceDTO) throws PropertyResourceExistingException {
        PropertyResource existingPropertyResource = getPropertyResourceByResourceAndProperty(propertyResourceDTO.getResourceDTO(), propertyResourceDTO.getProperty());
        if(existingPropertyResource == null){
            propertyResourceDAO.save(modelMapper.map(propertyResourceDTO, PropertyResource.class));
            logger.info("Relacion entre resource y property creada existosamente");
        }else{
            logger.info("Relacion entre resource y property ya existia");
            throw  new PropertyResourceExistingException("Ya existe esta relacion");
        }
    }

    public List<PropertyResourceDTO> getPropertyResourcesByProperties(List<PropertyDTO> properties) {
        List<PropertyResourceDTO> propertyResourceDTOs = new ArrayList<>();
        ArrayList<Integer> idProperties = new ArrayList<Integer>();
        for (PropertyDTO propertyDTO : properties) {
            idProperties.add(propertyDTO.getPropertyId());
        }
        ArrayList<PropertyResource> resources = propertyResourceDAO.findAll();
        for (PropertyResource resource : resources) {
            if(idProperties.contains(resource.getProperty().getPropertyId())){
                propertyResourceDTOs.add(modelMapper.map(resource, PropertyResourceDTO.class));
            }
        }

        return propertyResourceDTOs;
    }


    public ArrayList<String> getResourcesTypeActives(){
        ArrayList<String> resourceTypes = new ArrayList<>();
        for (PropertyResource resource : propertyResourceDAO.findAll()) {
            if(resource.getResourceAvailability().equals("true")) {
                resourceTypes.add(resource.getResource().getResourceType());
            }
        }
        return resourceTypes;
    }

    public PropertyResource getPropertyResourceByResourceAndProperty(ResourceDTO resourceDTO, PropertyDTO property) {

        for (PropertyResource propertyResource : propertyResourceDAO.findAll()) {
            if (propertyResource.getResource().equals(modelMapper.map(resourceDTO, Resource.class))){
                if(propertyResource.getProperty().equals(modelMapper.map(property, Property.class))) {
                    return propertyResource;
                }
            }
        }
        return null;
    }
}
