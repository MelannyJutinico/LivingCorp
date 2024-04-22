package unbosque.edu.co.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ServiceRequestDTO {

    private int requestId;
    private LocalDateTime requestDateTime;
    private WebUserDTO userName;
    private PropertyDTO propertyId;
    private ServiceProviderDTO serviceProviderId;
    private String requestDescription;
    private LocalDateTime serviceDateTime;

    public ServiceRequestDTO(LocalDateTime pRequestDateTime, WebUserDTO pUserName, PropertyDTO pPropertyId, ServiceProviderDTO pServiceId, String pRequestDescription, LocalDateTime pServiceDateTime) {
        this.requestDateTime = pRequestDateTime;
        this.userName = pUserName;
        this.propertyId = pPropertyId;
        this.serviceProviderId = pServiceId;
        this.requestDescription = pRequestDescription;
        this.serviceDateTime = pServiceDateTime;
    }

    public ServiceRequestDTO(int requestId,LocalDateTime pRequestDateTime, WebUserDTO pUserName, PropertyDTO pPropertyId, ServiceProviderDTO pServiceId, String pRequestDescription, LocalDateTime pServiceDateTime) {
        this.requestId = requestId;
        this.requestDateTime = pRequestDateTime;
        this.userName = pUserName;
        this.propertyId = pPropertyId;
        this.serviceProviderId = pServiceId;
        this.requestDescription = pRequestDescription;
        this.serviceDateTime = pServiceDateTime;
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

    public WebUserDTO getUserName() {
        return userName;
    }

    public void setUserName(WebUserDTO pUserName) {
        userName = pUserName;
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
