package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.model.dto.PropertyResidentDTO;
import unbosque.edu.co.livingcorp.model.dto.ResourceBookingDTO;
import unbosque.edu.co.livingcorp.service.PropertyResidentService;
import unbosque.edu.co.livingcorp.service.ResourceBookingService;

import java.io.Serializable;
import java.util.ArrayList;

@Named(value="adminReportsBean")
@ApplicationScoped
public class AdminReportsBean implements Serializable {

    @Inject
    private ResourceBookingService resourceBookingService;

    @Inject
    private PropertyResidentService propertyResidentService;

    private ArrayList<ResourceBookingDTO> resourceBookings;
    private ArrayList<PropertyResidentDTO> propertyResidents;



    @PostConstruct
    public void init() {
        resourceBookings = (ArrayList<ResourceBookingDTO>) resourceBookingService.findAllResourceBookings();
        propertyResidents = propertyResidentService.getPropertyResidents();
    }

    public long getTotalCountResidents(String name) {
        return propertyResidents.stream().filter(resident -> name.equals(resident.getProperty().getPropertyName())).count();
    }
    public long getTotalCountBookings(String name) {

        return resourceBookings.stream().filter(booking -> name.equals(booking.getPropertyResource().getResource().getResourceType())).count();
    }


    public ArrayList<ResourceBookingDTO> getResourceBookings() {
        return resourceBookings;
    }

    public void setResourceBookings(ArrayList<ResourceBookingDTO> resourceBookings) {
        this.resourceBookings = resourceBookings;
    }

    public ArrayList<PropertyResidentDTO> getPropertyResidents() {
        return propertyResidents;
    }

    public void setPropertyResidents(ArrayList<PropertyResidentDTO> propertyResidents) {
        this.propertyResidents = propertyResidents;
    }
}
