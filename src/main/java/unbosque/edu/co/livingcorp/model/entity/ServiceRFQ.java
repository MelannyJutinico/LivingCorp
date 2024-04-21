package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SERVICE_RFQ")
public class ServiceRFQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RFQ_ID")
    private int rfqId;
    @Column(name = "RFQ_DATETIME")
    private LocalDateTime rfqDateTime;
    @ManyToOne
    @JoinColumn(name = "USER_NAME" , referencedColumnName = "USER_NAME")
    private WebUser userName;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID" , referencedColumnName = "PROPERTY_ID")
    private Property propertyId;
    @ManyToOne
    @JoinColumn(name = "SVC_PROVIDER_ID" , referencedColumnName = "PROVIDER_ID")
    private ServiceProvider serviceProviderId;
    @Column(name = "REQUEST_DESCRIPTION")
    private String requestDescription;

    public ServiceRFQ( LocalDateTime pRfqDateTime, WebUser pUserName, Property pPropertyId, ServiceProvider pServiceId, String pRequestDescription) {
        rfqDateTime = pRfqDateTime;
        userName = pUserName;
        propertyId = pPropertyId;
        serviceProviderId = pServiceId;
        requestDescription = pRequestDescription;
    }

    public ServiceRFQ() {}

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

}