package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;

import java.io.Serializable;
import java.util.ArrayList;

@Named(value="indexBean")
@ApplicationScoped
public class IndexBean implements Serializable {

    private ArrayList<PropertyDTO> properties = new ArrayList<>();

    //Filtros
    private int filterMinPrice = 10000;
    private int filterMaxPrice = 500000;

    @Inject
    private PropertyManagementService propertyManagementService;

    @PostConstruct
    public void initProperties(){
        properties = propertyManagementService.listProperties();
    }

    public ArrayList<PropertyDTO> getProperties() {
        return properties;
    }

    public int getFilterMinPrice() {
        return filterMinPrice;
    }

    public void setFilterMinPrice(int filterMinPrice) {
        this.filterMinPrice = filterMinPrice;
    }

    public int getFilterMaxPrice() {
        return filterMaxPrice;
    }

    public void setFilterMaxPrice(int filterMaxPrice) {
        this.filterMaxPrice = filterMaxPrice;
    }

    public void setProperties(ArrayList<PropertyDTO> properties) {
        this.properties = properties;
    }
}
