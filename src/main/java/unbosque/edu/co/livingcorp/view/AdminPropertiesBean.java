package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.PropertyCreateException;
import unbosque.edu.co.livingcorp.exception.PropertyUpdateException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Named(value="adminPropertiesBean")
@ApplicationScoped
public class AdminPropertiesBean implements Serializable {

    @Inject
    private PropertyManagementService propertyManagementService;

    private ArrayList<PropertyDTO> properties = new ArrayList<>();
    private PropertyDTO selectedProperty;
    private PropertyDTO newProperty;



    public void openNew(){
        this.newProperty = new PropertyDTO();
    }

    @PostConstruct
    public void initialize() {
        listResources();
    }

    public void listResources() {
        properties = propertyManagementService.listProperties();
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


    public void createProperty() {
        try {
            propertyManagementService.createProperty(newProperty);
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
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
}
