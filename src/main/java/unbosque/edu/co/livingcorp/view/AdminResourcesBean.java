package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import unbosque.edu.co.livingcorp.model.dto.ResourceDTO;
import unbosque.edu.co.livingcorp.service.ResourceManagementService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value="adminResourcesBean")
@ApplicationScoped
public class AdminResourcesBean implements Serializable {

    private static final Logger log = LogManager.getLogger(AdminResourcesBean.class);

    @Inject
    private ResourceManagementService resourceManagementService;

    private ArrayList<ResourceDTO> resources = new ArrayList<>();
    private ResourceDTO resource = new ResourceDTO();

    @PostConstruct
    public void initialize() {
        listResources();
    }

    public void listResources() {
        resources = resourceManagementService.listResources();
    }

    public void registerResource() {
        listResources();
        resourceManagementService.registerResource(resource);
    }

    public void eliminateResource() {
        resourceManagementService.eliminateResource(resource);
        PrimeFaces.current().ajax().update("formAdminResource:resourceTable");    }

    public ArrayList<ResourceDTO> getResources() {
        return resources;
    }

    public void setResources(ArrayList<ResourceDTO> resources) {
        this.resources = resources;
    }

    public ResourceDTO getResource() {
        return resource;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

}
