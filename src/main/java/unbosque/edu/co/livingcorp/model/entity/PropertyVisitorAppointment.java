package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "PROPERTY_VISITOR_APPOINTMENT")
public class PropertyVisitorAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private int appointmentId;
    @Column(name = "VISITOR_NAME")
    private String visitorName;
    @Column(name = "ADVISOR_NAME")
    private String advisorName;
    @Column(name = "APPOINTMENT_DATETIME")
    private LocalDate appointmentDate;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    public PropertyVisitorAppointment(int appointmentId, String visitorName, String advisorName, LocalDate appointmentDate, Property property) {
        this.appointmentId = appointmentId;
        this.visitorName = visitorName;
        this.advisorName = advisorName;
        this.appointmentDate = appointmentDate;
        this.property = property;
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
