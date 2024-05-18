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



    public ResourceBookingDTO(int bookingId,String pUserName, PropertyResourceDTO propertyResourceDTO, LocalDateTime pBookingDateTime, LocalDateTime pBookingStartDate, LocalDateTime pBookingEndDate, double pBookingCost, boolean pPaymentComplete) {
        this.bookingId = bookingId;
        this.userName = pUserName;
        this.propertyResource = propertyResourceDTO;
        this.bookingDateTime = pBookingDateTime;
        this.bookingStartDate = pBookingStartDate;
        this.bookingEndDate = pBookingEndDate;
        this.bookingCost = pBookingCost;
        this.paymentComplete = pPaymentComplete;
    }

        public ResourceBookingDTO() {}

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

        public PropertyResourceDTO getPropertyResource() {
            return propertyResource;
        }

        public void setPropertyResource(PropertyResourceDTO propertyResourceDTO) {
            this.propertyResource = propertyResourceDTO;
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

    @Override
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
}
