package unbosque.edu.co.livingcorp.model.dto;

import java.time.LocalDate;
import java.util.Date;

public class PropertyVisitorAppointmentDTO {

    private int appointmentId;
    private String visitorName;
    private String advisorName;
    private Date appointmentDate;
    private PropertyDTO property;


    public PropertyVisitorAppointmentDTO(int appointmentId,String visitorName, String advisorName, Date appointmentDate, PropertyDTO property  ){
       this.appointmentId = appointmentId;
        this.visitorName = visitorName;
        this.advisorName = advisorName;
        this.appointmentDate = appointmentDate;
        this.property = property;
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

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public PropertyDTO getProperty() {
        return property;
    }

    public void setProperty(PropertyDTO propertyId) {
        this.property = propertyId;
    }


}
