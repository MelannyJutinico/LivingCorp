package unbosque.edu.co.livingcorp.model.dto;

import unbosque.edu.co.livingcorp.model.entity.ServiceProvider;

import java.time.LocalDateTime;
import java.util.Date;

public class ServiceRFQDTO {

    private int rfqId;
    private LocalDateTime rfqDateTime;
    private WebUserDTO user;
    private PropertyDTO property;
    private ServiceProviderDTO serviceProvider;
    private String requestDescription;


    public ServiceRFQDTO(int rfqId,LocalDateTime pRfqDateTime, WebUserDTO user, PropertyDTO pProperty, ServiceProviderDTO pService, String pRequestDescription) {
        this.rfqId = rfqId;
        this.rfqDateTime = pRfqDateTime;
        this.user = user;
        this.property = pProperty;
        this.serviceProvider = pService;
        this.requestDescription = pRequestDescription;
    }

    public ServiceRFQDTO() {}

    public int getRfqId() {
        return rfqId;
    }

    public void setRfqId(int pRfqId) {
        rfqId = pRfqId;
    }

    public LocalDateTime getRfqDateTime() {
        return rfqDateTime;
    }

    public void setRfqDateTime(LocalDateTime pRfqDateTime) {
        rfqDateTime = pRfqDateTime;
    }

    public WebUserDTO getUser() {
        return user;
    }

    public void setUser(WebUserDTO user) {
        this.user = user;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO pProperty) {
        property = pProperty;
    }

    public ServiceProviderDTO getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProviderDTO pServiceId) {
        serviceProvider = pServiceId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String pRequestDescription) {
        requestDescription = pRequestDescription;
    }

}