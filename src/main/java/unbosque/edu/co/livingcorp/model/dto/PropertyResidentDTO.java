package unbosque.edu.co.livingcorp.model.dto;

import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;

public class PropertyResidentDTO {

    private Integer propertyResidentId;
    private PropertyDTO property;
    private WebUserDTO userName;
    private boolean isOwner;

    public PropertyResidentDTO(Integer propertyResidentId, PropertyDTO property, WebUserDTO userName, boolean isOwner) {
        this.propertyResidentId = propertyResidentId;
        this.property = property;
        this.userName = userName;
        this.isOwner = isOwner;
    }

    public PropertyResidentDTO(PropertyDTO property, WebUserDTO userName, boolean isOwner) {
        this.property = property;
        this.userName = userName;
        this.isOwner = isOwner;
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

    public WebUserDTO getUserName() {
        return userName;
    }

    public void setUserName(WebUserDTO userName) {
        this.userName = userName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
