package unbosque.edu.co.livingcorp.model.dto;


public class ResourceDTO {

    private Integer resourceId;
    private String resourceDescription;
    private String resourceType;

    public ResourceDTO(Integer resourceId, String resourceDescription, String resourceType) {
        this.resourceId = resourceId;
        this.resourceDescription = resourceDescription;
        this.resourceType = resourceType;
    }

    public ResourceDTO() {}

    public ResourceDTO(String resourceDescription, String resourceType) {
        this.resourceDescription = resourceDescription;
        this.resourceType = resourceType;

    public ResourceDTO() {

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
