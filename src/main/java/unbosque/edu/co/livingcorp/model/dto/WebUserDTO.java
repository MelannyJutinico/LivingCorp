package unbosque.edu.co.livingcorp.model.dto;

import java.time.LocalDateTime;

public class WebUserDTO {

    private String userName;
    private String userEmail;
    private String userPassword;
    private LocalDateTime lastLogin;
    private boolean isBlocked;
    private boolean isPropertyAdmin;
    private boolean isResidentPropertyOwner;


    public WebUserDTO(String userName, String email, String password, LocalDateTime lastLogin, boolean isBlocked, boolean isPropertyAdmin, boolean isResidentPropertyOwner) {
        this.userName = userName;
        this.userEmail = email;
        this.userPassword = password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isPropertyAdmin() {
        return isPropertyAdmin;
    }

    public void setPropertyAdmin(boolean propertyAdmin) {
        isPropertyAdmin = propertyAdmin;
    }

    public boolean isResidentPropertyOwner() {
        return isResidentPropertyOwner;
    }

    public void setResidentPropertyOwner(boolean residentPropertyOwner) {
        isResidentPropertyOwner = residentPropertyOwner;
    }

}


