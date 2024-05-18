package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import unbosque.edu.co.livingcorp.exception.ResourceCreateException;
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
    private ResourceDTO selectedResource = new ResourceDTO();

    @PostConstruct
    public void initialize() {
        listResources();
    }

    public void listResources() {
        resources = resourceManagementService.listResources();
    }

    public void registerResource() {
        listResources();
        try {
            resourceManagementService.registerResource(resource);
            listResources();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resource Created", "Resource created successfully"));
        } catch (ResourceCreateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resource Not Created", e.getMessage()));
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public void updateResource() {
        listResources();
        try {
            resourceManagementService.updateResource(resource);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Resource Updated", "Resource updated successfully"));
        } catch (ResourceCreateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resource Not Updated", e.getMessage()));
            throw new RuntimeException(e);
        }
    }



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

    public ResourceDTO getSelectedResource() {
        return selectedResource;
    }

    public void setSelectedResource(ResourceDTO selectedResource) {
        this.selectedResource = selectedResource;
    }
}
