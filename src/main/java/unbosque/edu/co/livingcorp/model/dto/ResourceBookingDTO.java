package unbosque.edu.co.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ResourceBookingDTO{

        private int bookingId;
        private PropertyResourceDTO propertyResourceDTO;
        private WebUserDTO webUserDTO;
        private LocalDateTime bookingDateTime;
        private LocalDateTime bookingStartDate;
        private LocalDateTime bookingEndDate;
        private double bookingCost;
        private boolean paymentComplete;



    public ResourceBookingDTO(int bookingId, PropertyResourceDTO propertyResourceIdDTO,WebUserDTO webUserDTO, LocalDateTime pBookingDateTime, LocalDateTime pBookingStartDate, LocalDateTime pBookingEndDate, double pBookingCost, boolean pPaymentComplete) {
        this.bookingId = bookingId;
        this.propertyResourceDTO = propertyResourceIdDTO;
        this.webUserDTO = webUserDTO;
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

        public PropertyResourceDTO getPropertyResourceDTO() {
            return propertyResourceDTO;
        }

        public void setPropertyResourceDTO(PropertyResourceDTO pPropertyResidentId) {
            propertyResourceDTO = pPropertyResidentId;
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

    public WebUserDTO getWebUserDTO() {
        return webUserDTO;
    }

    public void setWebUserDTO(WebUserDTO webUserDTO) {
        this.webUserDTO = webUserDTO;
    }

    public boolean isPaymentComplete() {
        return paymentComplete;
    }
}
