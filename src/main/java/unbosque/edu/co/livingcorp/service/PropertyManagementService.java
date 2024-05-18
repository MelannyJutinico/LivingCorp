package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.PropertyCreateException;
import unbosque.edu.co.livingcorp.exception.PropertyUpdateException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResourceDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.model.entity.Property;
import unbosque.edu.co.livingcorp.model.entity.PropertyResource;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class PropertyManagementService implements Serializable {

    @Inject
    private InterfaceDAO<Property, Integer> propertyDAO;

    @Inject
    private InterfaceDAO<PropertyResource, Integer> propertyResourceDAO;


    private final ModelMapper modelMapper = new ModelMapper();

    private static final Logger logger = LogManager.getLogger(PropertyManagementService.class);

    public void createProperty(PropertyDTO propertyDTO, WebUserDTO webUser) throws PropertyCreateException, NoSuchAlgorithmException {
        Optional<PropertyDTO> existingProperty = getPropertyByNameAndCity(propertyDTO.getPropertyName(), propertyDTO.getPropertyCity());
        logger.info("Comprobando si la propiedad ya existe en la ciudad...");
        if (existingProperty.isPresent()) {
            logger.info("Propiedad ya existente...");
            throw new PropertyCreateException("Ya existe una propiedad con ese nombre en esa ciudad");
        }



        propertyDTO.setUser(webUser);
        propertyDAO.save(modelMapper.map(propertyDTO, Property.class));
        logger.info("Propiedad creada exitosamente...");
    }



    public void updateProperty(PropertyDTO propertyDTO) throws PropertyUpdateException {
        try {
            Optional<Property> existingProperty = Optional.ofNullable(propertyDAO.findById(propertyDTO.getPropertyId()));
            logger.info("Comprobando si la propiedad existe...");

            if (existingProperty.isPresent()) {
                Property property = existingProperty.get();

                // Actualizar los campos de la propiedad con los valores del DTO
                property.setPropertyName(propertyDTO.getPropertyName());
                property.setPropertyCity(propertyDTO.getPropertyCity());
                property.setPropertyDescription(propertyDTO.getPropertyDescription());
                property.setPropertyPrice(propertyDTO.getPropertyPrice());
                property.setPropertyBathrooms(propertyDTO.getPropertyBathrooms());
                property.setPropertyRooms(propertyDTO.getPropertyRooms());
                property.setAvailableForSale(propertyDTO.isAvailableForSale());
                // Puedes actualizar otros campos aquí

                propertyDAO.update(property); // Actualizar la propiedad en la base de datos
                logger.info("Propiedad actualizada exitosamente...");
            } else {
                logger.info("Propiedad no encontrada...");
                throw new PropertyUpdateException("No se encontró la propiedad a actualizar");
            }
        } catch (Exception e) {
            logger.error("Error al actualizar la propiedad: " + e.getMessage());
            throw new PropertyUpdateException("Error al actualizar la propiedad");
        }
    }

    private Optional<PropertyDTO> getPropertyByNameAndCity(String name, String city) {
        for (Property property : propertyDAO.findAll()) {
            if (property.getPropertyName().equals(name) && property.getPropertyCity().equals(city)) {
                return Optional.of(modelMapper.map(property, PropertyDTO.class));
            }
        }
        return Optional.empty();
    }

    public PropertyDTO getPropertyByName(String name) {
        for (Property property : propertyDAO.findAll()) {
            if (property.getPropertyName().equals(name)) {
                return modelMapper.map(property, PropertyDTO.class);
            }
        }
        return null;
    }


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

    public ArrayList<String> getNameProperties(WebUserDTO admin){
        logger.info("Obteniendo nombres de las propiedades...");
        ArrayList<String> names = new ArrayList<>();
        for(Property property : propertyDAO.findAll()){
            if(property.getUser().getUserName().equals(admin.getUserName())){
                names.add(property.getPropertyName());
            }
        }
        return names;
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





    public ArrayList<PropertyDTO> getPropertiesByAdmin(WebUserDTO admin){
        ArrayList<PropertyDTO> properties = new ArrayList<>();
        for(Property property : propertyDAO.findAll()){
            if(property.getUser().getUserName().equals(admin.getUserName())){
                properties.add(modelMapper.map(property, PropertyDTO.class));
            }
        }
        return properties;
    }



}
