package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.PropertyCreateException;
import unbosque.edu.co.livingcorp.exception.PropertyUpdateException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

@Named(value="adminPropertiesBean")
@ApplicationScoped
public class AdminPropertiesBean implements Serializable {

    @Inject
    private PropertyManagementService propertyManagementService;

    private ArrayList<PropertyDTO> properties = new ArrayList<>();
    private PropertyDTO selectedProperty;
    private PropertyDTO newProperty;
    private WebUserDTO currentAdmin = new WebUserDTO();



    public void openNew(){
        this.newProperty = new PropertyDTO();
    }

    @PostConstruct
    public void initialize() {
        currentAdmin = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        listResources();

    }

    public void listResources() {
        properties = propertyManagementService.getPropertiesByAdmin(currentAdmin);
    }

    public ArrayList<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<PropertyDTO> properties) {
        this.properties = properties;
    }

    public PropertyDTO getSelectedProperty() {
        return selectedProperty;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }

    public void createProperty() {
        try {
            propertyManagementService.createProperty(newProperty, currentAdmin);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Property Created", "Property created successfully"));
            listResources();
        } catch (PropertyCreateException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear", e.getMessage()));
        } catch (NoSuchAlgorithmException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear", e.getMessage()));
            throw new RuntimeException(e);
        }
    }


    public void updateProperty(){
            try {
                propertyManagementService.updateProperty(selectedProperty);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Property Updated"));
                listResources();
            } catch (PropertyUpdateException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

    }

    public void setSelectedProperty(PropertyDTO selectedProperty) {
        this.selectedProperty = selectedProperty;
    }



    public PropertyDTO getNewProperty() {
        return newProperty;
    }

    public void setNewProperty(PropertyDTO newProperty) {
        this.newProperty = newProperty;
    }

    public WebUserDTO getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(WebUserDTO currentAdmin) {
        this.currentAdmin = currentAdmin;
    }
}
