package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.InvalidMinTimeException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResourceDTO;
import unbosque.edu.co.livingcorp.model.dto.ResourceBookingDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;
import unbosque.edu.co.livingcorp.service.PropertyResidentService;
import unbosque.edu.co.livingcorp.service.ResourceBookingService;
import unbosque.edu.co.livingcorp.service.UserService;

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
    private ArrayList<PropertyDTO> userPropertiesDTO;
    private PropertyDTO propertyDTO;
    private ArrayList<PropertyResourceDTO> propertyResourcesDTO;
    private ResourceBookingDTO resourceBookingDTO;
    private ArrayList<PropertyDTO> residentPropertiesDTO;

    @PostConstruct
    public void init() {
        userPropertiesDTO = new ArrayList<>();
        propertyDTO = new PropertyDTO();
        propertyResourcesDTO = new ArrayList<>();
        resourceBookingDTO = new ResourceBookingDTO();
        residentPropertiesDTO = new ArrayList<>();
        getResidentProperties();
    }


    public void getUserProperties() {
        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        userPropertiesDTO = (ArrayList<PropertyDTO>) userService.getUserProperties(webUserDTO);
    }

    public void getResidentProperties(){
        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        residentPropertiesDTO = propertyResidentService.getPropertiesByResident(webUserDTO);
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
        var propertyResourceDTO = (PropertyResourceDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("propertyResourceDTO");
        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        resourceBookingDTO.setPropertyResourceDTO(propertyResourceDTO);
        resourceBookingDTO.setWebUserDTO(webUserDTO);
        resourceBookingDTO.setBookingDateTime(LocalDateTime.now());
        boolean paymentComplete = resourceBookingDTO.isPaymentComplete();

        try {
            resourceBookingDTO.setPaymentComplete(paymentComplete);
            resourceBookingDTO.setBookingCost(resourceBookingService.calculatePaymentAmount(resourceBookingDTO));resourceBookingDTO.setPaymentComplete(false);
            resourceBookingService.saveResourceBooking(resourceBookingDTO);
        } catch (InvalidMinTimeException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha invalida: ", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ArrayList<PropertyDTO> getUserPropertiesDTO() {
        return userPropertiesDTO;
    }

    public void setUserPropertiesDTO(ArrayList<PropertyDTO> userPropertiesDTO) {
        this.userPropertiesDTO = userPropertiesDTO;
    }

    public PropertyManagementService getPropertyManagementService() {
        return propertyManagementService;
    }

    public void setPropertyManagementService(PropertyManagementService propertyManagementService) {
        this.propertyManagementService = propertyManagementService;
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

    public PropertyResidentService getPropertyResidentService() {
        return propertyResidentService;
    }

    public void setPropertyResidentService(PropertyResidentService propertyResidentService) {
        this.propertyResidentService = propertyResidentService;
    }

    public ArrayList<PropertyDTO> getResidentPropertiesDTO() {
        return residentPropertiesDTO;
    }

    public void setResidentPropertiesDTO(ArrayList<PropertyDTO> residentPropertiesDTO) {
        this.residentPropertiesDTO = residentPropertiesDTO;
    }



}
