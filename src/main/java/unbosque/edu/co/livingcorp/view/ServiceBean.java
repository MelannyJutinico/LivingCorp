package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.ServiceRFQDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.PropertyResidentService;
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
    private ServiceRFQDTO serviceRfqDTO;
    private List<PropertyDTO> propertiesDTO;
    private PropertyDTO propertyDTO;

    @PostConstruct
    public void init() {
        serviceRfqDTO = new ServiceRFQDTO();
        propertiesDTO = new ArrayList<>();
        propertyDTO = new PropertyDTO();
    }

    public void saveServiceRfq() {


        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        serviceRfqDTO.setRfqDateTime(LocalDateTime.now());
        serviceRfqDTO.setUser(webUserDTO);

        try {
            serviceRfqAPIService.createServiceRFQ(null);
        } catch (ObjectAPICreateException e) {
            throw new RuntimeException(e);
        }
    }


    public void getProperties(){
        var webUser = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        propertiesDTO = propertyResidentService.getPropertiesByResident(webUser);
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
}
