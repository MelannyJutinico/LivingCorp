package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unbosque.edu.co.livingcorp.exception.InvalidMinTimeException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResourceDTO;
import unbosque.edu.co.livingcorp.model.dto.ResourceBookingDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Named(value="userBean")
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;
    @Inject
    private PropertyManagementService propertyManagementService;
    @Inject
    private ResourceBookingService resourceBookingService;
    @Inject
    private PropertyResidentService propertyResidentService;
    @Inject
    private PropertyResourceService propertyResourceService;

    private ArrayList<PropertyDTO> userPropertiesDTO;
    private PropertyDTO propertyDTO;
    private ArrayList<PropertyResourceDTO> propertyResourcesDTO;
    private ResourceBookingDTO resourceBookingDTO;
    private ArrayList<PropertyDTO> residentPropertiesDTO;
    private PropertyResourceDTO propertyResourceDTO;
    private WebUserDTO currentUser;

    private static final Logger logger = LogManager.getLogger(ResourceManagementService.class);

    @PostConstruct
    public void init() {
        currentUser = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        userPropertiesDTO = new ArrayList<>();
        propertyDTO = new PropertyDTO();
        propertyResourcesDTO = new ArrayList<>();
        resourceBookingDTO = new ResourceBookingDTO();
        residentPropertiesDTO = new ArrayList<>();
        getResidentProperties();
    }


    public void getUserProperties() {
        userPropertiesDTO = (ArrayList<PropertyDTO>) userService.getUserProperties(currentUser);
    }

    public void getResidentProperties(){
        residentPropertiesDTO = propertyResidentService.getPropertiesByResident(currentUser);
        if (residentPropertiesDTO == null) {
            residentPropertiesDTO = new ArrayList<>();
        }
    }

    public String checkPropertyDetails(Integer id) {
        propertyDTO = propertyManagementService.findPropertyById(id);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pId", propertyDTO.getPropertyId());
        return "customerPropertyDetailsPage.xhtml";
    }

    public ArrayList<PropertyResourceDTO> getPropertyResources(){
        var id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pId");
        propertyResourcesDTO = (ArrayList<PropertyResourceDTO>) propertyManagementService.getPropertyResources(id);
        return propertyResourcesDTO;
    }



    public void saveResourceBooking(){

        resourceBookingDTO.setPropertyResource(propertyResourceDTO);
        resourceBookingDTO.setUserName(currentUser.getUserName());

        resourceBookingDTO.setBookingDateTime(LocalDateTime.now());

        try {
            resourceBookingDTO.setPaymentComplete(resourceBookingDTO.isPaymentComplete());
            resourceBookingDTO.setBookingCost(resourceBookingService.calculatePaymentAmount(resourceBookingDTO));resourceBookingDTO.setPaymentComplete(false);
            resourceBookingService.saveResourceBooking(resourceBookingDTO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recurso solicitado existosamente", "Su total a pagar es: "+resourceBookingDTO.getBookingCost()));
        } catch (InvalidMinTimeException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha invalida: ", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void capturePropertyResource(int id){
        propertyResourceDTO = propertyResourceService.getPropertyResourceById(id);
    }


    public ArrayList<PropertyDTO> getUserPropertiesDTO() {
        return userPropertiesDTO;
    }

    public void setUserPropertiesDTO(ArrayList<PropertyDTO> userPropertiesDTO) {
        this.userPropertiesDTO = userPropertiesDTO;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }

    public ArrayList<PropertyResourceDTO> getPropertyResourcesDTO() {
        return propertyResourcesDTO;
    }

    public void setPropertyResourcesDTO(ArrayList<PropertyResourceDTO> propertyResourcesDTO) {
        this.propertyResourcesDTO = propertyResourcesDTO;
    }



    public ResourceBookingDTO getResourceBookingDTO() {
        return resourceBookingDTO;
    }

    public void setResourceBookingDTO(ResourceBookingDTO resourceBookingDTO) {
        this.resourceBookingDTO = resourceBookingDTO;
    }



    public ArrayList<PropertyDTO> getResidentPropertiesDTO() {
        return residentPropertiesDTO;
    }

    public void setResidentPropertiesDTO(ArrayList<PropertyDTO> residentPropertiesDTO) {
        this.residentPropertiesDTO = residentPropertiesDTO;
    }

    public WebUserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(WebUserDTO currentUser) {
        this.currentUser = currentUser;
    }

    public PropertyResourceDTO getPropertyResourceDTO() {
        return propertyResourceDTO;
    }

    public void setPropertyResourceDTO(PropertyResourceDTO propertyResourceDTO) {
        this.propertyResourceDTO = propertyResourceDTO;
    }
}
