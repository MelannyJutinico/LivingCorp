package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "SVC_PROVIDERS")
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVIDER_ID")
    private int providerId;
    @Column(name = "PROVIDER_EMAIL")
    private String providerEmail;
    @Column(name = "SERVICE_DESCRIPTION")
    private String serviceDescription;
    @Column(name = "SERVICE_TYPE")
    private String serviceType;
    @Column(name = "SERVICE_PRICE")
    private String servicePrice;

    public ServiceProvider(String pProviderEmail, String pServiceDescription, String pServiceType, String pServicePrice) {
        this.providerEmail = pProviderEmail;
        this.serviceDescription = pServiceDescription;
        this.serviceType = pServiceType;
        this.servicePrice = pServicePrice;
    }

    public ServiceProvider() {}

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int pProviderId) {
        providerId = pProviderId;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String pProviderEmail) {
        providerEmail = pProviderEmail;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String pServiceDescription) {
        serviceDescription = pServiceDescription;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String pServiceType) {
        serviceType = pServiceType;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String pServicePrice) {
        servicePrice = pServicePrice;
    }

}
