package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PROPERTY_VISITOR_APPOINTMENT")
public class PropertyVisitorAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "")
    private int appointmentId;
    @Column(name = "")
    private String visitorName;
    @Column(name = "")
    private String advisorName;
    @Column(name = "")
    private LocalDate appointmentDate;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID", referencedColumnName = "PROPERTY_ID")
    private Property propertyId;

    public PropertyVisitorAppointment(String visitorName, String advisorName, LocalDate appointmentDate, Property propertyId  ){
        this.visitorName = visitorName;
        this.advisorName = advisorName;
        this.appointmentDate = appointmentDate;
    }

    public PropertyVisitorAppointment() {}

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
