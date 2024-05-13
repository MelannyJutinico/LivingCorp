package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.ResourceDTO;
import unbosque.edu.co.livingcorp.model.entity.Resource;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.util.ArrayList;

@Stateless
public class ResourceManagementService {

    @Inject
    private InterfaceDAO<Resource, Integer> resourceDAO;

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

    public void registerResource(ResourceDTO resourceDTO) {
        if(resourceDTO.getResourceId()==null) {
            resourceDAO.save(modelMapper.map(resourceDTO, Resource.class));
        }
    }

    public void eliminateResource(ResourceDTO resourceDTO) {
        if(resourceDTO.getResourceId()!=null) {
            resourceDAO.delete(modelMapper.map(resourceDTO, Resource.class));
        }
    }

}
