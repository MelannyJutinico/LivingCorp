package unbosque.edu.co.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ServiceRequestDTO {

    private int requestId;
    private LocalDateTime requestDateTime;
    private WebUserDTO user;
    private PropertyDTO propertyId;
    private ServiceProviderDTO serviceProviderId;
    private String requestDescription;
    private LocalDateTime serviceDateTime;


    public ServiceRequestDTO(int requestId, LocalDateTime requestDateTime, WebUserDTO user, PropertyDTO propertyId, ServiceProviderDTO serviceProviderId, String requestDescription, LocalDateTime serviceDateTime) {
        this.requestId = requestId;
        this.requestDateTime = requestDateTime;
        this.user = user;
        this.propertyId = propertyId;
        this.serviceProviderId = serviceProviderId;
        this.requestDescription = requestDescription;
        this.serviceDateTime = serviceDateTime;
    }

    public ServiceRequestDTO() {}

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int pRequestId) {
        requestId = pRequestId;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime pRequestDateTime) {
        requestDateTime = pRequestDateTime;
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

    public ServiceProviderDTO getServiceProviderId() {
        return serviceProviderId;
    }

    public void setServiceProviderId(ServiceProviderDTO pServiceId) {
        serviceProviderId = pServiceId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String pRequestDescription) {
        requestDescription = pRequestDescription;
    }

    public LocalDateTime getServiceDateTime() {
        return serviceDateTime;
    }

    public void setServiceDateTime(LocalDateTime pServiceDateTime) {
        serviceDateTime = pServiceDateTime;
    }

}
