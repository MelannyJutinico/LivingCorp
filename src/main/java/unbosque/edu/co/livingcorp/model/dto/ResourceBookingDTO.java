package unbosque.edu.co.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ResourceBookingDTO{

        private int bookingId;
        private String userName;
        private PropertyResourceDTO propertyResource;
        private LocalDateTime bookingDateTime;
        private LocalDateTime bookingStartDate;
        private LocalDateTime bookingEndDate;
        private double bookingCost;
        private boolean paymentComplete;


    public ResourceBookingDTO(int bookingId, String userName, PropertyResourceDTO propertyResource, LocalDateTime bookingDateTime, LocalDateTime bookingStartDate, LocalDateTime bookingEndDate, double bookingCost, boolean paymentComplete) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.propertyResource = propertyResource;
        this.bookingDateTime = bookingDateTime;
        this.bookingStartDate = bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.bookingCost = bookingCost;
        this.paymentComplete = paymentComplete;
    }

    public ResourceBookingDTO() {}

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public PropertyResourceDTO getPropertyResource() {
        return propertyResource;
    }

    public void setPropertyResource(PropertyResourceDTO propertyResource) {
        this.propertyResource = propertyResource;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public LocalDateTime getBookingStartDate() {
        return bookingStartDate;
    }

    public void setBookingStartDate(LocalDateTime bookingStartDate) {
        this.bookingStartDate = bookingStartDate;
    }

    public LocalDateTime getBookingEndDate() {
        return bookingEndDate;
    }

    public void setBookingEndDate(LocalDateTime bookingEndDate) {
        this.bookingEndDate = bookingEndDate;
    }

    public double getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(double bookingCost) {
        this.bookingCost = bookingCost;
    }

    public void setPaymentComplete(boolean paymentComplete) {
        this.paymentComplete = paymentComplete;
    }

    public String toString() {
        return "ResourceBookingDTO{" +
                "bookingId=" + bookingId +
                ", userName='" + userName + '\'' +
                ", propertyResourceDTO=" + propertyResource +
                ", bookingDateTime=" + bookingDateTime +
                ", bookingStartDate=" + bookingStartDate +
                ", bookingEndDate=" + bookingEndDate +
                ", bookingCost=" + bookingCost +
                ", paymentComplete=" + paymentComplete +
                '}';

    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }
}
