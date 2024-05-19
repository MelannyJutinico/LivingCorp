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
    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private WebUser webUser;
    @ManyToOne
    @JoinColumn(name = "PROP_RES_ID")
    private PropertyResource propertyResource;
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


    public ResourceBooking(int bookingId, WebUser webUser, PropertyResource propertyResource, LocalDateTime bookingDateTime, LocalDateTime bookingStartDate, LocalDateTime bookingEndDate, double bookingCost, boolean paymentComplete) {
        this.bookingId = bookingId;
        this.webUser = webUser;
        this.propertyResource = propertyResource;
        this.bookingDateTime = bookingDateTime;
        this.bookingStartDate = bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.bookingCost = bookingCost;
        this.paymentComplete = paymentComplete;
    }

    public ResourceBooking() {}

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int pBookingId) {
        bookingId = pBookingId;
    }

    public PropertyResource getPropertyResource() {
        return propertyResource;
    }

    public void setPropertyResource(PropertyResource propertyResource) {
        this.propertyResource = propertyResource;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
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

    public WebUser getUserName() {
        return webUser;
    }

    public void setUserName(WebUser webUser) {
        this.webUser = webUser;
    }

    @Override
    public String toString() {
        return "ResourceBooking{" +
                "bookingId=" + bookingId +
                ", userName='" + webUser.getUserName() + '\'' +
                ", propertyResource=" + propertyResource +
                ", bookingDateTime=" + bookingDateTime +
                ", bookingStartDate=" + bookingStartDate +
                ", bookingEndDate=" + bookingEndDate +
                ", bookingCost=" + bookingCost +
                ", paymentComplete=" + paymentComplete +
                '}';
    }
}