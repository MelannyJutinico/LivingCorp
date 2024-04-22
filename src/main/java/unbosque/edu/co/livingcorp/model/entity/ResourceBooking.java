package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESOURCE_BOOKINGS")
public class ResourceBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private int bookingId;
    @Column(name = "USER_NAME")
    private String userName;
    @ManyToOne
    @JoinColumn(name = "PROP_RES_ID", referencedColumnName = "PROP_RES_ID")
    private PropertyResource propertyResourceId;
    @Column(name = "BOOKING_DATETIME")
    private LocalDateTime bookingDateTime;
    @Column(name = "BOOKING_START_DATE")
    private LocalDateTime bookingStartDate;
    @Column(name = "BOOKING_END_DATE")
    private LocalDateTime bookingEndDate;
    @Column(name = "BOOKING_COST")
    private double bookingCost;
    @Column(name = "PAYMENT_COMPLETE")
    private boolean paymentComplete;

    public ResourceBooking( String pUserName, PropertyResource pPropertyResidentId, LocalDateTime pBookingDateTime, LocalDateTime pBookingStartDate, LocalDateTime pBookingEndDate, double pBookingCost, boolean pPaymentComplete) {
        this.userName = pUserName;
        this.propertyResourceId = pPropertyResidentId;
        this.bookingDateTime = pBookingDateTime;
        this.bookingStartDate = pBookingStartDate;
        this.bookingEndDate = pBookingEndDate;
        this.bookingCost = pBookingCost;
        this.paymentComplete = pPaymentComplete;
    }

    public ResourceBooking() {}

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int pBookingId) {
        bookingId = pBookingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String pUserName) {
        userName = pUserName;
    }

    public PropertyResource getPropertyResourceId() {
        return propertyResourceId;
    }

    public void setPropertyResourceId(PropertyResource pPropertyResidentId) {
        propertyResourceId = pPropertyResidentId;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime pBookingDateTime) {
        bookingDateTime = pBookingDateTime;
    }

    public LocalDateTime getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(LocalDateTime pBookingStartDate) {
        bookingStartDate = pBookingStartDate;
    }

    public LocalDateTime getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(LocalDateTime pBookingEndDate) {
        bookingEndDate = pBookingEndDate;
    }

    public double getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(double pBookingCost) {
        bookingCost = pBookingCost;
    }

    public boolean getPaymentComplete() {
        return paymentComplete;
    }

    public void setPaymentComplete(boolean pPaymentComplete) {
        paymentComplete = pPaymentComplete;
    }

}