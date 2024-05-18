package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.PropertyResidentDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.model.entity.PropertyResident;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import unbosque.edu.co.livingcorp.exception.ResidentCreateException;
import java.io.Serializable;
import java.util.List;

@Stateless
public class PropertyResidentService implements Serializable {

    @Inject
    private InterfaceDAO<PropertyResident, Integer> propertyResidentDAO;

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

}
