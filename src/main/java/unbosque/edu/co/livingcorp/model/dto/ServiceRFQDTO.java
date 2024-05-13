package unbosque.edu.co.livingcorp.model.dto;

import unbosque.edu.co.livingcorp.model.entity.ServiceProvider;
import java.time.LocalDateTime;

public class ServiceRFQDTO {

    private int rfqId;
    private LocalDateTime rfqDateTime;
    private WebUserDTO user;
    private PropertyDTO propertyId;
    private ServiceProvider serviceProviderId;
    private String requestDescription;


    public ServiceRFQDTO(int rfqId,LocalDateTime pRfqDateTime, WebUserDTO user, PropertyDTO pPropertyId, ServiceProvider pServiceId, String pRequestDescription) {
        this.rfqId = rfqId;
        this.rfqDateTime = pRfqDateTime;
        this.user = user;
        this.propertyId = pPropertyId;
        this.serviceProviderId = pServiceId;
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

    public PropertyDTO getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(PropertyDTO pPropertyId) {
        propertyId = pPropertyId;
    }

    public ServiceProvider getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(ServiceProvider pServiceId) {
        serviceProviderId = pServiceId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String pRequestDescription) {
        requestDescription = pRequestDescription;
    }

}