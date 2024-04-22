package unbosque.edu.co.livingcorp.model.dto;

public class ServiceProviderDTO {

    private int providerId;
    private String providerEmail;
    private String serviceDescription;
    private String serviceType;
    private String servicePrice;

    public ServiceProviderDTO(String pProviderEmail, String pServiceDescription, String pServiceType, String pServicePrice) {
        this.providerEmail = pProviderEmail;
        this.serviceDescription = pServiceDescription;
        this.serviceType = pServiceType;
        this.servicePrice = pServicePrice;
    }

    public ServiceProviderDTO(int providerId,String pProviderEmail, String pServiceDescription, String pServiceType, String pServicePrice) {
        this.providerId = providerId;
        this.providerEmail = pProviderEmail;
        this.serviceDescription = pServiceDescription;
        this.serviceType = pServiceType;
        this.servicePrice = pServicePrice;
    }

    public ServiceProviderDTO() {}

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
