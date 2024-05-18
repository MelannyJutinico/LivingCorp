package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.PropertyVisitorAppointmentDTO;
import unbosque.edu.co.livingcorp.model.entity.PropertyVisitorAppointment;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class VisitorService implements Serializable {

    @Inject
    private InterfaceDAO<PropertyVisitorAppointment, Integer> visitorDAO;

    private final ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger = LogManager.getLogger(PropertyManagementService.class);


    public void registerVisitor(PropertyVisitorAppointmentDTO visitor) {
        logger.info("Guardando visita...");
        visitorDAO.save(modelMapper.map(visitor, PropertyVisitorAppointment.class));
    }
}
