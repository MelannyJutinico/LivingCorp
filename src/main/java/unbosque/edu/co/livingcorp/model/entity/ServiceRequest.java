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
    @JoinColumn(name = "USER_NAME")
    private WebUser user;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "PROPERTY_ID")
    private Property property;
    @ManyToOne
    @JoinColumn(name = "SVC_PROVIDER_ID", referencedColumnName = "PROVIDER_ID")
    private ServiceProvider serviceProvider;
    @Column(name = "REQUEST_DESCRIPTION")
    private String requestDescription;
    @Column(name = "SVC_DATETIME")
    private LocalDateTime serviceDateTime;

    public ServiceRequest(int requestId, LocalDateTime requestDateTime, WebUser user, Property property, ServiceProvider serviceProvider, String requestDescription, LocalDateTime serviceDateTime) {
        this.requestId = requestId;
        this.requestDateTime = requestDateTime;
        this.user = user;
        this.property = property;
        this.serviceProvider = serviceProvider;
        this.requestDescription = requestDescription;
        this.serviceDateTime = serviceDateTime;
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

    public LocalDateTime getServiceDateTime() {
        return serviceDateTime;
    }

    public void setServiceDateTime(LocalDateTime pServiceDateTime) {
        serviceDateTime = pServiceDateTime;
    }

}
