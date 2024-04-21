package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "SERVICE_REQUESTS")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RQST_ID")
    private int requestId;
    @Column(name = "RQST_DATETIME")
    private LocalDateTime requestDateTime;
    @ManyToOne
    @JoinColumn(name = "USER_NAME", referencedColumnName = "USER_NAME")
    private WebUser userName;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "PROPERTY_ID")
    private Property propertyId;
    @ManyToOne
    @JoinColumn(name = "SVC_PROVIDER_ID", referencedColumnName = "PROVIDER_ID")
    private ServiceProvider serviceProviderId;
    @Column(name = "REQUEST_DESCRIPTION")
    private String requestDescription;
    @Column(name = "SVC_DATETIME")
    private LocalDateTime serviceDateTime;

    public ServiceRequest(LocalDateTime pRequestDateTime, WebUser pUserName, Property pPropertyId, ServiceProvider pServiceId, String pRequestDescription, LocalDateTime pServiceDateTime) {
        requestDateTime = pRequestDateTime;
        userName = pUserName;
        propertyId = pPropertyId;
        serviceProviderId = pServiceId;
        requestDescription = pRequestDescription;
        serviceDateTime = pServiceDateTime;
    }

    public ServiceRequest() {}

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

    public WebUser getUserName() {
        return userName;
    }

    public void setUserName(WebUser pUserName) {
        userName = pUserName;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property pPropertyId) {
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

    public LocalDateTime getServiceDateTime() {
        return serviceDateTime;
    }

    public void setServiceDateTime(LocalDateTime pServiceDateTime) {
        serviceDateTime = pServiceDateTime;
    }

}
