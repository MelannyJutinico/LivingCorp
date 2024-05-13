package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import unbosque.edu.co.livingcorp.exception.ResidentCreateException;
import unbosque.edu.co.livingcorp.exception.UserAuthenticationException;
import unbosque.edu.co.livingcorp.exception.UserRegistrationException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResidentDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyVisitorAppointmentDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.model.entity.PropertyVisitorAppointment;
import unbosque.edu.co.livingcorp.service.PropertyManagementService;
import unbosque.edu.co.livingcorp.service.PropertyResidentService;
import unbosque.edu.co.livingcorp.service.UserService;
import unbosque.edu.co.livingcorp.service.VisitorService;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named(value="indexBean")
@SessionScoped
public class IndexBean implements Serializable {

    private ArrayList<PropertyDTO> properties = new ArrayList<>();
    private WebUserDTO webUser = new WebUserDTO();
    private boolean authenticated;
    private PropertyDTO propertySelected = new PropertyDTO();
    private PropertyResidentDTO  propertyResidentDTO = new PropertyResidentDTO();
    private ArrayList<String> usersSelected = new ArrayList<>();
    private PropertyVisitorAppointmentDTO visitor = new PropertyVisitorAppointmentDTO();

    private String filterCity;
    private String filterNameProperty;
    private int filterMinPrice;
    private int filterMaxPrice;
    private int filterNumberRooms;
    private int filterNumberBathrooms;
    private List<String> filterRentSale;


    @Inject
    private PropertyManagementService propertyManagementService;

    @Inject
    private UserService userService;

    @Inject
    private PropertyResidentService propertyResidentService;

    @Inject
    private VisitorService propertyVisitorService;

    @PostConstruct
    public void initProperties(){
        properties = propertyManagementService.listProperties();
    }

    public void registerUser(){
        try{
            userService.registerUser(webUser);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "El usuario se ha registrado correctamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            resetFields();
        } catch (UserRegistrationException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o correo ya ocupado", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (NoSuchAlgorithmException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }

    public void login() {
        try {
            webUser = userService.authenticateUser(webUser);
            if (webUser != null && webUser.isLoginCorrect() && !webUser.isBlocked()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", webUser);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lastLogin", LocalDateTime.now());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ingreso exitoso", "Usuario logeado exitosamente");
                authenticated = true;

            }else if (webUser != null && webUser.isBlocked()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de autenticación", "La cuenta está bloqueada. Comuníquese con el administrador.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                resetFields();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de autenticación", "Credenciales incorrectas.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            }
        } catch (NoSuchAlgorithmException | UserAuthenticationException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al autenticar usuario", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }
    public void resetFields() {
        webUser = new WebUserDTO();
        filterCity = null;
        filterNameProperty = null;
        filterMinPrice = 0;
        filterMaxPrice = 0;
        filterNumberRooms = 0;
        filterNumberBathrooms = 0;
        filterRentSale = null;
    }

    public void purchase(){
        try {
            propertyResidentService.purchaseProperty(propertySelected, webUser, propertyResidentDTO, usersSelected);
            properties = propertyManagementService.listProperties();
        }catch (ResidentCreateException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al relacionar usuario con la propiedad", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra exitosa", "Su compra ha sido exitosa");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public void rent(){
        try{
            propertyResidentService.rentProperty(propertySelected, webUser, usersSelected);
            properties = propertyManagementService.listProperties();
        }catch (ResidentCreateException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al relacionar usuario con la propiedad", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public ArrayList<String> suggestCity(String query) {
        String queryLowerCase = query.toLowerCase();
        ArrayList<String> cities = propertyManagementService.getCities();
        return (ArrayList<String>) cities.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public ArrayList<String> getUserNames(){
        return userService.getUserNames(webUser.getUserName());
    }

    public ArrayList<String> suggestName(String query) {
        String queryLowerCase = query.toLowerCase();
        ArrayList<String> names = propertyManagementService.getNameProperties();
        return (ArrayList<String>) names.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }

    public void registrerVisitor(){
        visitor.setAdvisorName(propertySelected.getUser().getUserName());
        visitor.setProperty(propertySelected);
        propertyVisitorService.registerVisitor(visitor);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro correcto","Su cita de visita ha sido agendada correctamente");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void filterProperties(){
        properties = propertyManagementService.listProperties();

        properties = (ArrayList<PropertyDTO>) properties.stream()
                .filter(property -> filterCity == null || property.getPropertyCity().equalsIgnoreCase(filterCity))
                .filter(property -> filterNameProperty == null || property.getPropertyName().toLowerCase().contains(filterNameProperty.toLowerCase()))
                .filter(property -> (filterMinPrice == 0 || property.getPropertyPrice() >= filterMinPrice) && (filterMaxPrice == 0 || property.getPropertyPrice() <= filterMaxPrice))
                .filter(property -> (filterNumberRooms == 0 || property.getPropertyRooms() >= filterNumberRooms))
                .filter(property -> (filterNumberBathrooms == 0 || property.getPropertyBathrooms() >= filterNumberBathrooms))
                .filter(property -> filterRentSale == null || filterRentSale.isEmpty() ||
                        (filterRentSale.contains("RENT") && property.isAvailableForRent()) ||
                        (filterRentSale.contains("SALE") && property.isAvailableForSale()))
                .collect(Collectors.toList());
    }

    public void restartProperties(){
        properties = propertyManagementService.listProperties();
        resetFields();
    }

    public ArrayList<PropertyDTO> getProperties() {
        return properties;
    }

    public String getFilterCity() {
        return filterCity;
    }

    public void setFilterCity(String filterCity) {
        this.filterCity = filterCity;
    }

    public void setProperties(ArrayList<PropertyDTO> properties) { this.properties = properties; }

    public String getFilterNameProperty() {
        return filterNameProperty;
    }

    public void setFilterNameProperty(String filterNameProperty) {
        this.filterNameProperty = filterNameProperty;
    }

    public int getFilterMinPrice() {
        return filterMinPrice;
    }

    public void setFilterMinPrice(int filterMinPrice) {
        this.filterMinPrice = filterMinPrice;
    }

    public int getFilterMaxPrice() {
        return filterMaxPrice;
    }

    public void setPropertyResidentDTO(PropertyResidentDTO propertyResidentDTO) {
        this.propertyResidentDTO = propertyResidentDTO;
    }

    public PropertyVisitorAppointmentDTO getVisitor() {
        return visitor;
    }

    public void setVisitor(PropertyVisitorAppointmentDTO visitor) {
        this.visitor = visitor;
    }

    public void setFilterMaxPrice(int filterMaxPrice) {
        this.filterMaxPrice = filterMaxPrice;
    }

    public int getFilterNumberRooms() {
        return filterNumberRooms;
    }

    public void setFilterNumberRooms(int filterNumberRooms) {
        this.filterNumberRooms = filterNumberRooms;
    }

    public int getFilterNumberBathrooms() {
        return filterNumberBathrooms;
    }

    public void setFilterNumberBathrooms(int filterNumberBathrooms) {this.filterNumberBathrooms = filterNumberBathrooms;}

    public List<String> getFilterRentSale() { return filterRentSale;}

    public void setFilterRentSale(List<String> filterRentSale) {this.filterRentSale = filterRentSale;}

    public WebUserDTO getWebUser() {
        return webUser;
    }
    public void setWebUser(WebUserDTO webUser) {
        this.webUser = webUser;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public PropertyDTO getPropertySelected() {
        return propertySelected;
    }

    public void setPropertySelected(PropertyDTO propertySelected) {
        this.propertySelected = propertySelected;
    }

    public ArrayList<String> getUsersSelected() {
        return usersSelected;
    }

    public void setUsersSelected(ArrayList<String> usersSelected) {
        this.usersSelected = usersSelected;
    }

    public PropertyResidentDTO getPropertyResidentDTO() {
        return propertyResidentDTO;
    }
}
