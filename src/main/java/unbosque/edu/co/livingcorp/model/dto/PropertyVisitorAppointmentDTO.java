package unbosque.edu.co.livingcorp.model.dto;


import unbosque.edu.co.livingcorp.model.entity.Property;

import java.time.LocalDate;

public class PropertyVisitorAppointmentDTO {

    private int appointmentId;
    private String visitorName;
    private String advisorName;
    private LocalDate appointmentDate;
    private Property propertyId;

    public PropertyVisitorAppointmentDTO(String visitorName, String advisorName, LocalDate appointmentDate, Property propertyId  ){
        this.visitorName = visitorName;
        this.advisorName = advisorName;
        this.appointmentDate = appointmentDate;
    }

    public PropertyVisitorAppointmentDTO(int appointmentId,String visitorName, String advisorName, LocalDate appointmentDate, Property propertyId  ){
        this.visitorName = visitorName;
        this.advisorName = advisorName;
        this.appointmentDate = appointmentDate;
    }

    public PropertyVisitorAppointmentDTO() {}

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public void setAdvisorName(String advisorName) {
        this.advisorName = advisorName;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }


}
