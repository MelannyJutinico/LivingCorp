package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.model.dto.*;
import unbosque.edu.co.livingcorp.service.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ServiceBean implements Serializable {

    @Inject
    private ServiceRfqAPIService serviceRfqAPIService;
    @Inject
    private PropertyResidentService propertyResidentService;
    @Inject
    private ServiceProviderAPIService serviceProviderAPIService;
    @Inject
    private ServiceRequestAPIService serviceRequestAPIService;
    @Inject
    private PropertyManagementService propertyManagementService;
    private ServiceRequestDTO serviceRequestDTO;
    private ServiceRFQDTO serviceRfqDTO;
    private List<PropertyDTO> propertiesDTO;
    private PropertyDTO propertyDTO;
    private ArrayList<ServiceProviderDTO> serviceProviderDTOs;
    private ServiceProviderDTO selectedServiceProviderDTO;
    private ArrayList<String> nameProperties = new ArrayList<>();
    private String nameProperty;


    @PostConstruct
    public void init() {
        serviceRfqDTO = new ServiceRFQDTO();
        propertiesDTO = new ArrayList<>();
        propertyDTO = new PropertyDTO();
        serviceProviderDTOs = new ArrayList<>();
        serviceRequestDTO = new ServiceRequestDTO();
        getServiceProviders();


    }

    public void saveServiceRfq() {
        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        serviceRfqDTO.setRfqDateTime(LocalDateTime.now());
        serviceRfqDTO.setUser(webUserDTO);
        var property = propertyManagementService.getPropertyByName(nameProperty);
        serviceRfqDTO.setProperty(property);
        serviceRfqDTO.setServiceProvider(selectedServiceProviderDTO);
        serviceRfqAPIService.createRFQ(serviceRfqDTO);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cotizacion realizada", "La cotizacion fue enviada correctamente"));
    }

    public void saveServiceRequest() {

        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        serviceRequestDTO.setRequestDateTime(LocalDateTime.now());
        serviceRequestDTO.setUser(webUserDTO);
        var property = propertyManagementService.getPropertyByName(nameProperty);
        serviceRequestDTO.setProperty(property);
        serviceRequestDTO.setServiceProvider(selectedServiceProviderDTO);
        serviceRequestAPIService.createRequest(serviceRequestDTO);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solcitud realizada", "La solicitud fue enviada correctamente"));

    }

    public ArrayList<String> getNameProperties(String query) {
        var webUser = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        nameProperties = propertyResidentService.getNameProperties(webUser);
        String queryLowerCase = query.toLowerCase();
        return (ArrayList<String>) nameProperties.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }


    public void getProperties() {
        var webUser = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        propertiesDTO = propertyResidentService.getPropertiesByResident(webUser);
    }

    public void getServiceProviders() {
        serviceProviderDTOs = (ArrayList<ServiceProviderDTO>) serviceProviderAPIService.getAllServiceProviders();
    }

    public void selectProvider() {
        serviceRequestDTO.setServiceProvider(selectedServiceProviderDTO);
    }

    public ServiceRfqAPIService getServiceRfqAPIService() {
        return serviceRfqAPIService;
    }

    public void setServiceRfqAPIService(ServiceRfqAPIService serviceRfqAPIService) {
        this.serviceRfqAPIService = serviceRfqAPIService;
    }

    public ServiceRFQDTO getServiceRfqDTO() {
        return serviceRfqDTO;
    }

    public void setServiceRfqDTO(ServiceRFQDTO serviceRfqDTO) {
        this.serviceRfqDTO = serviceRfqDTO;
    }

    public List<PropertyDTO> getPropertiesDTO() {
        return propertiesDTO;
    }

    public void setPropertiesDTO(List<PropertyDTO> propertiesDTO) {
        this.propertiesDTO = propertiesDTO;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }

    public PropertyResidentService getPropertyResidentService() {
        return propertyResidentService;
    }

    public void setPropertyResidentService(PropertyResidentService propertyResidentService) {
        this.propertyResidentService = propertyResidentService;
    }

    public ServiceProviderAPIService getServiceProviderAPIService() {
        return serviceProviderAPIService;
    }

    public void setServiceProviderAPIService(ServiceProviderAPIService serviceProviderAPIService) {
        this.serviceProviderAPIService = serviceProviderAPIService;
    }


    public ServiceRequestAPIService getServiceRequestAPIService() {
        return serviceRequestAPIService;
    }

    public void setServiceRequestAPIService(ServiceRequestAPIService serviceRequestAPIService) {
        this.serviceRequestAPIService = serviceRequestAPIService;
    }

    public ServiceRequestDTO getServiceRequestDTO() {
        return serviceRequestDTO;
    }

    public void setServiceRequestDTO(ServiceRequestDTO serviceRequestDTO) {
        this.serviceRequestDTO = serviceRequestDTO;
    }

    public ArrayList<ServiceProviderDTO> getServiceProviderDTOs() {
        return serviceProviderDTOs;
    }


    public void setServiceProviderDTOs(ArrayList<ServiceProviderDTO> serviceProviderDTOs) {
        this.serviceProviderDTOs = serviceProviderDTOs;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }


    public void setNameProperties(ArrayList<String> nameProperties) {
        this.nameProperties = nameProperties;
    }

    public ServiceProviderDTO getSelectedServiceProviderDTO() {
        return selectedServiceProviderDTO;
    }

    public void setSelectedServiceProviderDTO(ServiceProviderDTO selectedServiceProviderDTO) {
        this.selectedServiceProviderDTO = selectedServiceProviderDTO;
    }

    public ArrayList<String> getNameProperties() {
        return nameProperties;
    }

    public PropertyManagementService getPropertyManagementService() {
        return propertyManagementService;
    }

    public void setPropertyManagementService(PropertyManagementService propertyManagementService) {
        this.propertyManagementService = propertyManagementService;
    }


}