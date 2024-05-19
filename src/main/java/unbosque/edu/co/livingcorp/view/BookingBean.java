package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.model.dto.ResourceBookingDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;
import unbosque.edu.co.livingcorp.service.PropertyResidentService;
import unbosque.edu.co.livingcorp.service.ResourceBookingService;

import java.io.Serializable;
import java.util.ArrayList;

@Named(value = "bookingBean")
@ViewScoped
public class BookingBean implements Serializable {

    @Inject
    private ResourceBookingService resourceBookingService;
    @Inject
    private PropertyResidentService propertyResidentService;
    @Inject
    private PropertyManagementService propertyManagementService;
    private ResourceBookingDTO resourceBookingDTO;
    private ArrayList<ResourceBookingDTO> resourceBookingDTOs;

    @PostConstruct
    public void init(){
        resourceBookingDTO = new ResourceBookingDTO();
        resourceBookingDTOs = new ArrayList();
        getResidentBookings();
        resourceBookingDTOs.forEach(e -> System.out.println(e));
    }

    public void getResidentBookings(){
        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        resourceBookingDTOs = (ArrayList<ResourceBookingDTO>) resourceBookingService.getBookingsByWebUser(webUserDTO);
        resourceBookingDTOs.forEach(e -> System.out.println(e));
    }

    public void cancelBooking(int id){
        var booking = resourceBookingService.findResourceBookingById(id);
        resourceBookingService.deleteResourceBooking(booking);
    }

    public ResourceBookingService getResourceBookingService() {
        return resourceBookingService;
    }

    public void setResourceBookingService(ResourceBookingService resourceBookingService) {
        this.resourceBookingService = resourceBookingService;
    }

    public ResourceBookingDTO getResourceBookingDTO() {
        return resourceBookingDTO;
    }

    public void setResourceBookingDTO(ResourceBookingDTO resourceBookingDTO) {
        this.resourceBookingDTO = resourceBookingDTO;
    }

    public ArrayList<ResourceBookingDTO> getResourceBookingDTOs() {
        return resourceBookingDTOs;
    }

    public void setResourceBookingDTOs(ArrayList<ResourceBookingDTO> resourceBookingDTOs) {
        this.resourceBookingDTOs = resourceBookingDTOs;
    }

    public PropertyResidentService getPropertyResidentService() {
        return propertyResidentService;
    }

    public void setPropertyResidentService(PropertyResidentService propertyResidentService) {
        this.propertyResidentService = propertyResidentService;
    }

    public PropertyManagementService getPropertyManagementService() {
        return propertyManagementService;
    }

    public void setPropertyManagementService(PropertyManagementService propertyManagementService) {
        this.propertyManagementService = propertyManagementService;
    }
}
