package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;

import java.io.Serializable;
import java.util.ArrayList;

@Named(value="adminPropertiesBean")
@ApplicationScoped
public class AdminPropertiesBean implements Serializable {

    @Inject
    private PropertyManagementService propertyManagementService;

    private ArrayList<PropertyDTO> properties = new ArrayList<>();
    private PropertyDTO property = new PropertyDTO();

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

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO property) {
        this.property = property;
    }
}
