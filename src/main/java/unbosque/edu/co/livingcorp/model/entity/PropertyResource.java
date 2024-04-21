package unbosque.edu.co.livingcorp.model.entity;
import jakarta.persistence.*;

@Entity @Table(name = "PROPERTY_RESOURCES")
public class PropertyResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROP_RES_ID")
    private Integer propResId;
    @ManyToOne @JoinColumn(name = "RESOURCE_ID")
    private Resource resource;
    @ManyToOne @JoinColumn(name = "PROPERTY_ID")
    private Property property;
    @Column(name = "RESOURCE_MIN_PRICE")
    private Double resourceMinPrice;
    @Column(name = "RESOURCE_MIN_TIME_HRS")
    private Integer resourceMinTimeHrs;
    @Column(name = "RESOURCE_AVAILABILITY")
    private String resourceAvailability;
    @Column(name = "RESOURCE_CAPACITY")
    private Integer resourceCapacity;
    @Column(name = "BOOKING_EMAIL")
    private String bookingEmail;

    public PropertyResource(Resource resource, Property property, Double resourceMinPrice, String resourceAvailability, Integer resourceMinTimeHrs, Integer resourceCapacity, String bookingEmail) {
        this.resource = resource;
        this.property = property;
        this.resourceMinPrice = resourceMinPrice;
        this.resourceAvailability = resourceAvailability;
        this.resourceMinTimeHrs = resourceMinTimeHrs;
        this.resourceCapacity = resourceCapacity;
        this.bookingEmail = bookingEmail;
    }

    public PropertyResource() {
    }

    public Integer getPropResId() {
        return propResId;
    }

    public void setPropResId(Integer propResId) {
        this.propResId = propResId;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Double getResourceMinPrice() {
        return resourceMinPrice;
    }

    public void setResourceMinPrice(Double resourceMinPrice) {
        this.resourceMinPrice = resourceMinPrice;
    }

    public Integer getResourceMinTimeHrs() {
        return resourceMinTimeHrs;
    }

    public void setResourceMinTimeHrs(Integer resourceMinTimeHrs) {
        this.resourceMinTimeHrs = resourceMinTimeHrs;
    }

    public String getResourceAvailability() {
        return resourceAvailability;
    }

    public void setResourceAvailability(String resourceAvailability) {
        this.resourceAvailability = resourceAvailability;
    }

    public Integer getResourceCapacity() {
        return resourceCapacity;
    }

    public void setResourceCapacity(Integer resourceCapacity) {
        this.resourceCapacity = resourceCapacity;
    }

    public String getBookingEmail() {
        return bookingEmail;
    }

    public void setBookingEmail(String bookingEmail) {
        this.bookingEmail = bookingEmail;
    }
}
