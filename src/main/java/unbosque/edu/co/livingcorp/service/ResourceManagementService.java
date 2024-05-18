package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.ResourceCreateException;
import unbosque.edu.co.livingcorp.model.dto.ResourceDTO;
import unbosque.edu.co.livingcorp.model.entity.Resource;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.util.ArrayList;

@Stateless
public class ResourceManagementService {

    @Inject
    private InterfaceDAO<Resource, Integer> resourceDAO;

    private static final Logger logger = LogManager.getLogger(ResourceManagementService.class);

    private final ModelMapper modelMapper = new ModelMapper();

    public ArrayList<ResourceDTO> listResources(){

        ArrayList<Resource> resources = resourceDAO.findAll();
        ArrayList<ResourceDTO> resourceDTOs = new ArrayList<>();

        for (Resource resource : resources) {
            resourceDTOs.add(modelMapper.map(resource, ResourceDTO.class));
        }
        return resourceDTOs;
    }

    public ResourceDTO consultResource(int id) {
        return modelMapper.map(resourceDAO.findById(id), ResourceDTO.class);
    }

    public ResourceDTO consultResourceByType(String type) {
        for (Resource resource : resourceDAO.findAll()) {
            if(resource.getResourceType().equalsIgnoreCase(type)) {
                return modelMapper.map(resource, ResourceDTO.class);
            }
        }
        return null;
    }

    public void registerResource(ResourceDTO resourceDTO) throws ResourceCreateException {
        ResourceDTO existingResource = consultResourceByType(resourceDTO.getResourceType());
        if(existingResource == null) {
            resourceDAO.save(modelMapper.map(resourceDTO, Resource.class));
            logger.info("Recurso creado");
        }else{
            logger.info("Recurso ya existente...");
            throw new ResourceCreateException("Ya existe este tipo de recurso");
        }
    }

    public void updateResource(ResourceDTO resourceDTO) throws ResourceCreateException {
        ResourceDTO existingResource = consultResourceByType(resourceDTO.getResourceType());
        if(existingResource != null) {

            resourceDAO.update(modelMapper.map(resourceDTO, Resource.class));
            logger.info("Recurso actualizado");
        }else{
            logger.info("Recurso ya existente con ese nombre...");
            throw new ResourceCreateException("Ya existe este tipo de recurso, use otro tipo");
        }
    }



}
