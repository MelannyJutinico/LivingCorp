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
    private WebUser userName;
    @Column(name = "IS_OWNER")
    private boolean isOwner;

    public PropertyResident() {
    }

    public PropertyResident(WebUser userName, Property property, boolean isOwner) {
        this.userName = userName;
        this.property = property;
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

    public WebUser getUserName() {
        return userName;
    }

    public void setUserName(WebUser userName) {
        this.userName = userName;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
