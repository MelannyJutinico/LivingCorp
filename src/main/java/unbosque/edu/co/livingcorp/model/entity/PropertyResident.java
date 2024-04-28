package unbosque.edu.co.livingcorp.model.entity;
import jakarta.persistence.*;
@Entity @Table(name = "PROPERTY_RESIDENTS")
public class PropertyResident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROPERTY_RESIDENT_ID")
    private Integer propertyResidentId;
    @ManyToOne @JoinColumn(name = "PROPERTY_ID")
    private Property property;
    @ManyToOne @JoinColumn(name = "USER_NAME")
    private WebUser user;
    @Column(name = "IS_OWNER")
    private boolean isOwner;

    public PropertyResident() {
    }

    public PropertyResident(Integer propertyResidentId, Property property, WebUser user, boolean isOwner) {
        this.propertyResidentId = propertyResidentId;
        this.property = property;
        this.user = user;
        this.isOwner = isOwner;
    }

    public Integer getPropertyResidentId() {
        return propertyResidentId;
    }

    public void setPropertyResidentId(Integer propertyResidentId) {
        this.propertyResidentId = propertyResidentId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public WebUser getUser() {
        return user;
    }

    public void setUser(WebUser user) {
        this.user = user;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
