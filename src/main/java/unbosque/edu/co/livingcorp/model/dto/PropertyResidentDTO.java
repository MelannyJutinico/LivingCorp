package unbosque.edu.co.livingcorp.model.dto;


public class PropertyResidentDTO {

    private Integer propertyResidentId;
    private PropertyDTO property;
    private WebUserDTO user;
    private boolean isOwner;

    public PropertyResidentDTO(Integer propertyResidentId, PropertyDTO property, WebUserDTO user, boolean isOwner) {
        this.propertyResidentId = propertyResidentId;
        this.property = property;
        this.user = user;
        this.isOwner = isOwner;
    }

    public PropertyResidentDTO() {
    }

    public Integer getPropertyResidentId() {
        return propertyResidentId;
    }

    public void setPropertyResidentId(Integer propertyResidentId) {
        this.propertyResidentId = propertyResidentId;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO property) {
        this.property = property;
    }

    public WebUserDTO getUser() {
        return user;
    }

    public void setUser(WebUserDTO user) {
        this.user = user;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    @Override
    public String toString() {
        return "PropertyResidentDTO{" +
                "propertyResidentId=" + propertyResidentId +
                ", property=" + property +
                ", userName=" + user +
                ", isOwner=" + isOwner +
                '}';
    }
}
