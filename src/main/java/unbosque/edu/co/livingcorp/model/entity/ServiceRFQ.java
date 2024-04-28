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
    private WebUser user;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID" , referencedColumnName = "PROPERTY_ID")
    private Property property;
    @ManyToOne
    @JoinColumn(name = "SVC_PROVIDER_ID")
    private ServiceProvider serviceProvider;
    @Column(name = "REQUEST_DESCRIPTION")
    private String requestDescription;

    public ServiceRFQ(int rfqId, LocalDateTime rfqDateTime, WebUser user, Property property, ServiceProvider serviceProvider, String requestDescription) {
        this.rfqId = rfqId;
        this.rfqDateTime = rfqDateTime;
        this.user = user;
        this.property = property;
        this.serviceProvider = serviceProvider;
        this.requestDescription = requestDescription;
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

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String pRequestDescription) {
        requestDescription = pRequestDescription;
    }

}