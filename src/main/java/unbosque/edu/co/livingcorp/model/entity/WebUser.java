package unbosque.edu.co.livingcorp.model.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WEB_USERS")
public class WebUser {

    @Id
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "USER_EMAIL", unique = true)
    private String userEmail;
    @Column(name = "USER_PASSWORD")
    private String userPassword;
    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;
    @Column(name = "IS_BLOCKED")
    private boolean isBlocked;
    @Column(name = "IS_PROPERTY_ADMIN")
    private boolean isPropertyAdmin;
    @Column(name = "IS_RESIDENT_PPRTY_OWNER")
    private boolean isResidentPropertyOwner;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Property> userProperties;


    public WebUser(String userName, String email, String password, LocalDateTime lastLogin, boolean isBlocked, boolean isPropertyAdmin, boolean isResidentPropertyOwner){
        this.userName = userName;
        this.userEmail = email;
        this.userPassword = password;
        this.lastLogin = lastLogin;
        this.isBlocked = isBlocked;
        this.isPropertyAdmin = isPropertyAdmin;
        this.isResidentPropertyOwner = isResidentPropertyOwner;
    }

    public WebUser(){}

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

    public List<Property> getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(List<Property> userProperties) {
        this.userProperties = userProperties;
    }
}
