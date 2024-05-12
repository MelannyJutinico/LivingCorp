package unbosque.edu.co.livingcorp.model.dto;

public class PropertyResourceDTO {

        private Integer propResId;
        private ResourceDTO resourceDTO;
        private PropertyDTO propertyDTO;
        private Double resourceMinPrice;
        private Integer resourceMinTimeHrs;
        private String resourceAvailability;
        private Integer resourceCapacity;
        private String bookingEmail;


        public PropertyResourceDTO(Integer propResId ,ResourceDTO resourceDTO, PropertyDTO propertyDTO, Double resourceMinPrice, String resourceAvailability, Integer resourceMinTimeHrs, Integer resourceCapacity, String bookingEmail) {

            this.propResId = propResId;
            this.resourceDTO = resourceDTO;
            this.propertyDTO = propertyDTO;
            this.resourceMinPrice = resourceMinPrice;
            this.resourceAvailability = resourceAvailability;
            this.resourceMinTimeHrs = resourceMinTimeHrs;
            this.resourceCapacity = resourceCapacity;
            this.bookingEmail = bookingEmail;
        }

        public PropertyResourceDTO() {
        }

        public Integer getPropResId() {
            return propResId;
        }

        public void setPropResId(Integer propResId) {
            this.propResId = propResId;
        }

        public ResourceDTO getResource() {
            return resourceDTO;
        }

        public void setResource(ResourceDTO resource) {
            this.resourceDTO = resource;
        }

        public PropertyDTO getProperty() {
            return propertyDTO;
        }

        public void setProperty(PropertyDTO property) {
            this.propertyDTO = property;
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


