package unbosque.edu.co.livingcorp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "RESOURCE_BOOKINGS")
public class ResourceBooking {

    @Id
    @Column(name = "BOOKING_ID")
    private int bookingId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PROP_RES_ID")
    private int propertyResidentId; 
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

    public ResourceBooking(int pBookingId, String pUserName, int pPropertyResidentId, LocalDateTime pBookingDateTime, LocalDateTime pBookingStartDate, LocalDateTime pBookingEndDate, double pBookingCost, boolean pPaymentComplete) {
        bookingId = pBookingId;
        userName = pUserName;
        propertyResidentId = pPropertyResidentId;
        bookingDateTime = pBookingDateTime;
        bookingStartDate = pBookingStartDate;
        bookingEndDate = pBookingEndDate;
        bookingCost = pBookingCost;
        paymentComplete = pPaymentComplete;
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

    public int getPropertyResidentId() {
        return propertyResidentId;
    }

    public void setPropertyResidentId(int pPropertyResidentId) {
        propertyResidentId = pPropertyResidentId;
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