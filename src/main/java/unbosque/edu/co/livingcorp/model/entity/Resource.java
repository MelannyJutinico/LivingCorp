package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RESOURCES")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_ID")
    private Integer resourceId;

    @Column(name = "RESOURCE_DESCRIPCION")
    private String resourceDescription;
    @Column(name = "RESOURCE_TYPE")
    private String resourceType;

    public Resource(Integer resourceId, String resourceDescription, String resourceType) {
        this.resourceId = resourceId;
        this.resourceDescription = resourceDescription;
        this.resourceType = resourceType;
    }

    public Resource() {

    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
