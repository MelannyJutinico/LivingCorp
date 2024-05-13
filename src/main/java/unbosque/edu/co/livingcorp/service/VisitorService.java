package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyVisitorAppointmentDTO;
import unbosque.edu.co.livingcorp.model.entity.PropertyVisitorAppointment;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Stateless
public class VisitorService implements Serializable {

    @Inject
    private InterfaceDAO<PropertyVisitorAppointment, Integer> visitorDAO;

    private final ModelMapper modelMapper = new ModelMapper();

    public void registerVisitor(PropertyVisitorAppointmentDTO visitor) {

        visitorDAO.save(modelMapper.map(visitor, PropertyVisitorAppointment.class));
    }
}
