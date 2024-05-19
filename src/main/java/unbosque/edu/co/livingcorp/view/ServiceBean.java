package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.ServiceProviderDTO;
import unbosque.edu.co.livingcorp.model.dto.ServiceRFQDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.PropertyResidentService;
import unbosque.edu.co.livingcorp.service.ServiceProviderAPIService;
import unbosque.edu.co.livingcorp.service.ServiceRfqAPIService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ServiceBean implements Serializable {

    @Inject
    private ServiceRfqAPIService serviceRfqAPIService;
    @Inject
    private PropertyResidentService propertyResidentService;
    @Inject
    private ServiceProviderAPIService serviceProviderAPIService;
    private ServiceRFQDTO serviceRfqDTO;
    private List<PropertyDTO> propertiesDTO;
    private PropertyDTO propertyDTO;
    private ArrayList<ServiceProviderDTO> serviceProviderDTOs;

    @PostConstruct
    public void init() {
        serviceRfqDTO = new ServiceRFQDTO();
        propertiesDTO = new ArrayList<>();
        propertyDTO = new PropertyDTO();
        serviceProviderDTOs = new ArrayList<>();
    }

    public void saveServiceRfq() {

        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        serviceRfqDTO.setRfqDateTime(LocalDateTime.now());
        serviceRfqDTO.setUser(webUserDTO);
        serviceRfqDTO.setRequestDescription("Funciono de maravilla");
        ServiceProviderDTO provider = new ServiceProviderDTO();
        provider.setProviderId(1);
        PropertyDTO property = new PropertyDTO();
        property.setPropertyId(5);
        serviceRfqDTO.setServiceProvider(provider);
        serviceRfqDTO.setProperty(property);
        try {
            serviceRfqAPIService.createServiceRFQ(serviceRfqDTO);
        } catch (ObjectAPICreateException e) {
            e.printStackTrace();
        }
    }

    public void getProperties(){
        var webUser = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        propertiesDTO = propertyResidentService.getPropertiesByResident(webUser);
    }

    //This method works properly
    public void getServiceProviders(){
       serviceProviderDTOs = (ArrayList<ServiceProviderDTO>) serviceProviderAPIService.getAllServiceProviders();
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
}
