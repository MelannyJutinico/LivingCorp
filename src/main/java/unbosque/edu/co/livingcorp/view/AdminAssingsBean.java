package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.PropertyResourceExistingException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResourceDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;
import unbosque.edu.co.livingcorp.service.PropertyResourceService;
import unbosque.edu.co.livingcorp.service.ResourceManagementService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named(value="adminAssingsBean")
@ApplicationScoped
public class AdminAssingsBean implements Serializable {

    @Inject
    private PropertyResourceService propertyResourceService;

    @Inject
    private PropertyManagementService propertyManagementService;
    @Inject
    private ResourceManagementService resourceManagementService;


    private PropertyResourceDTO propertyResource = new PropertyResourceDTO();
    private ArrayList<PropertyDTO> properties = new ArrayList<>();
    private List<PropertyResourceDTO> propertyResourceDTOs = new ArrayList<>();
    private WebUserDTO currentAdmin = new WebUserDTO();

    private String nameProperty;
    private String resource;

    @PostConstruct
    public void init() {
        currentAdmin = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        properties = propertyManagementService.getPropertiesByAdmin(currentAdmin);
        listResources();
    }

    public long getTotalCount(String name) {
        return propertyResourceDTOs.stream().filter(resource -> name.equals(resource.getProperty().getPropertyName())).count();
    }

    public ArrayList<String> suggestName(String query) {
        String queryLowerCase = query.toLowerCase();
        ArrayList<String> names = propertyManagementService.getNameProperties(currentAdmin);
        return (ArrayList<String>) names.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public ArrayList<String> getResourcesType(String query){
        String queryLowerCase = query.toLowerCase();
        ArrayList<String> types = resourceManagementService.getResourcesType();
        return (ArrayList<String>) types.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void listResources(){
        propertyResourceDTOs = propertyResourceService.getPropertyResourcesByProperties(properties);
    }

    public void createResourceProperty(){
        try {
            propertyResource.setProperty(propertyManagementService.getPropertyByName(nameProperty));
            propertyResource.setResource(resourceManagementService.consultResourceByType(resource));
            propertyResourceService.createPropertyResource(propertyResource);
            listResources();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Property Resource Relation Created", "The relation was created successfully"));
        }catch (PropertyResourceExistingException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating", e.getMessage()));
        }

    }

    public PropertyResourceDTO getPropertyResource() {
        return propertyResource;
    }

    public ArrayList<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<PropertyDTO> properties) {
        this.properties = properties;
    }

    public List<PropertyResourceDTO> getPropertyResourceDTOs() {
        return propertyResourceDTOs;
    }

    public void setPropertyResourceDTOs(ArrayList<PropertyResourceDTO> propertyResourceDTOs) {
        this.propertyResourceDTOs = propertyResourceDTOs;
    }

    public void setPropertyResource(PropertyResourceDTO propertyResource) {
        this.propertyResource = propertyResource;
    }

    public WebUserDTO getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(WebUserDTO currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
