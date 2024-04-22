package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;

import java.io.Serializable;
import java.util.ArrayList;

@Named(value="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private ArrayList<PropertyDTO> properties = new ArrayList<>();

    @Inject
    private PropertyManagementService propertyManagementService;

    @PostConstruct
    public void initProperties(){
        properties = propertyManagementService.listProperties();
    }

    public ArrayList<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<PropertyDTO> properties) {
        this.properties = properties;
    }
}
