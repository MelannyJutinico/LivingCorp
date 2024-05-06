package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named(value="indexBean")
@ApplicationScoped
public class IndexBean implements Serializable {

    private ArrayList<PropertyDTO> properties = new ArrayList<>();
    private WebUserDTO webUser = new WebUserDTO();

    private String filterCity;
    private String filterNameProperty;
    private int filterMinPrice;
    private int filterMaxPrice;
    private int filterNumberRooms;
    private int filterNumberBathrooms;
    private List<String> filterRentSale;


    @Inject
    private PropertyManagementService propertyManagementService;

    @PostConstruct
    public void initProperties(){
        properties = propertyManagementService.listProperties();
    }

    public ArrayList<String> suggestCity(String query) {
        String queryLowerCase = query.toLowerCase();
        ArrayList<String> cities = propertyManagementService.getCities();
        return (ArrayList<String>) cities.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public ArrayList<String> suggestName(String query) {
        String queryLowerCase = query.toLowerCase();
        ArrayList<String> names = propertyManagementService.getNameProperties();
        return (ArrayList<String>) names.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void filterProperties(){
        properties = propertyManagementService.listProperties();

        properties = (ArrayList<PropertyDTO>) properties.stream()
                .filter(property -> filterCity == null || property.getPropertyCity().equalsIgnoreCase(filterCity))
                .filter(property -> filterNameProperty == null || property.getPropertyName().toLowerCase().contains(filterNameProperty.toLowerCase()))
                .filter(property -> (filterMinPrice == 0 || property.getPropertyPrice() >= filterMinPrice) && (filterMaxPrice == 0 || property.getPropertyPrice() <= filterMaxPrice))
                .filter(property -> (filterNumberRooms == 0 || property.getPropertyRooms() >= filterNumberRooms))
                .filter(property -> (filterNumberBathrooms == 0 || property.getPropertyBathrooms() >= filterNumberBathrooms))
                .filter(property -> filterRentSale == null || filterRentSale.isEmpty() ||
                        (filterRentSale.contains("RENT") && property.isAvailableForRent()) ||
                        (filterRentSale.contains("SALE") && property.isAvailableForSale()))
                .collect(Collectors.toList());
    }

    public void restartProperties(){
        properties = propertyManagementService.listProperties();
    }

    public ArrayList<PropertyDTO> getProperties() {
        return properties;
    }

    public String getFilterCity() {
        return filterCity;
    }

    public void setFilterCity(String filterCity) {
        this.filterCity = filterCity;
    }

    public void setProperties(ArrayList<PropertyDTO> properties) { this.properties = properties; }

    public String getFilterNameProperty() {
        return filterNameProperty;
    }

    public void setFilterNameProperty(String filterNameProperty) {
        this.filterNameProperty = filterNameProperty;
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

    public int getFilterNumberRooms() {
        return filterNumberRooms;
    }

    public void setFilterNumberRooms(int filterNumberRooms) {
        this.filterNumberRooms = filterNumberRooms;
    }

    public int getFilterNumberBathrooms() {
        return filterNumberBathrooms;
    }

    public void setFilterNumberBathrooms(int filterNumberBathrooms) {this.filterNumberBathrooms = filterNumberBathrooms;}

    public List<String> getFilterRentSale() { return filterRentSale;}

    public void setFilterRentSale(List<String> filterRentSale) {this.filterRentSale = filterRentSale;}
}
