package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResidentDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.model.entity.Property;
import unbosque.edu.co.livingcorp.model.entity.PropertyResident;
import unbosque.edu.co.livingcorp.model.entity.WebUser;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import unbosque.edu.co.livingcorp.exception.ResidentCreateException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PropertyResidentService implements Serializable {

    @Inject
    private InterfaceDAO<PropertyResident, Integer> propertyResidentDAO;

    @Inject
    private InterfaceDAO<Property, Integer> propertyDAO;

    @Inject
    private InterfaceDAO <WebUser, String> userDAO;

    private final ModelMapper modelMapper = new ModelMapper();

    public void createPropertyResident(PropertyResidentDTO propertyResident) throws ResidentCreateException {
        if (residentExists(propertyResident)) {
            throw new ResidentCreateException("Residente ya relacionado con esa propiedad");
        }
        propertyResidentDAO.save(modelMapper.map(propertyResident, PropertyResident.class));
    }

    public boolean residentExists(PropertyResidentDTO propertyResident) {
        List<PropertyResident> allResidents = propertyResidentDAO.findAll();

        for (PropertyResident resident : allResidents) {
            if (resident.getProperty().getPropertyId() == propertyResident.getProperty().getPropertyId() &&
                    resident.getUser().getUserName().equals(propertyResident.getUser().getUserName())) {
                return true;
            }
        }

        return false;
    }

    public  void purchaseProperty(PropertyDTO propertyDTO, WebUserDTO userDTO, PropertyResidentDTO residentDTO, ArrayList<String> usersSelected) throws ResidentCreateException {
        WebUser user = userDAO.findById(userDTO.getUserName());
        user.setResidentPropertyOwner(true);
        userDAO.update(user);
        WebUserDTO webUserDTO = modelMapper.map(user, WebUserDTO.class);

        Property property = propertyDAO.findById(propertyDTO.getPropertyId());
        property.setAvailableForSale(false);
        PropertyDTO propertyDTO1 = modelMapper.map(propertyDAO.update(property), PropertyDTO.class);

        residentDTO.setUser(webUserDTO);
        residentDTO.setProperty(propertyDTO1);
        residentDTO.setOwner(true);

        createPropertyResident(residentDTO);


        for (String name : usersSelected) {
            WebUserDTO webUserResidentDTO = modelMapper.map(userDAO.findById(name), WebUserDTO.class);
            PropertyResidentDTO residentI = new PropertyResidentDTO();
            residentI.setUser(webUserResidentDTO);
            residentI.setProperty(propertyDTO1);
            residentI.setOwner(false);
            createPropertyResident(residentI);


        }
    }

    public void rentProperty(PropertyDTO propertySelected, WebUserDTO webUserDTO, ArrayList<String> usersSelected) throws ResidentCreateException {

        Property property = propertyDAO.findById(propertySelected.getPropertyId());
        property.setAvailableForRent(false);
        PropertyDTO propertyDTO = modelMapper.map(propertyDAO.update(property), PropertyDTO.class);

        PropertyResidentDTO residentDTO  = new PropertyResidentDTO();
        residentDTO.setUser(webUserDTO);
        residentDTO.setProperty(propertyDTO);
        residentDTO.setOwner(false);

        createPropertyResident(residentDTO);

        for(String name : usersSelected){
            WebUserDTO webUserResidentDTO = modelMapper.map(userDAO.findById(name), WebUserDTO.class);
            PropertyResidentDTO residentI = new PropertyResidentDTO();
            residentI.setUser(webUserResidentDTO);
            residentI.setProperty(propertyDTO);
            residentI.setOwner(false);
            createPropertyResident(residentDTO);
        }

    }


}
