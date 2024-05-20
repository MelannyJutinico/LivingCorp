package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.InvalidMinTimeException;
import unbosque.edu.co.livingcorp.model.dto.ResourceBookingDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.model.entity.Resource;
import unbosque.edu.co.livingcorp.model.entity.ResourceBooking;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import unbosque.edu.co.livingcorp.model.sender.EmailSender;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceBookingService implements Serializable {

    @Inject
    private InterfaceDAO<ResourceBooking, Integer> resourceBookingDAO;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger = LogManager.getLogger(ResourceBookingService.class);

    public ResourceBookingDTO saveResourceBooking(ResourceBookingDTO resourceBookingDTO) {
        logger.info("Creando reserva y enviando correo...");
        sendEmail(resourceBookingDTO);
        ResourceBooking resourceBooking = modelMapper.map(resourceBookingDTO, ResourceBooking.class);
        return modelMapper.map(resourceBookingDAO.save(resourceBooking),ResourceBookingDTO.class);
    }

    public ResourceBookingDTO findResourceBookingById(Integer id) {
        return modelMapper
                .map(resourceBookingDAO
                        .findById(id),ResourceBookingDTO.class);
    }

    public void deleteResourceBooking(ResourceBookingDTO resourceBookingDTO) {
        resourceBookingDAO
                .delete(modelMapper
                        .map(resourceBookingDTO, ResourceBooking.class));
    }

    public List<ResourceBookingDTO> findAllResourceBookings() {
        return resourceBookingDAO.findAll()
                .stream()
                .map(resourceBooking -> modelMapper.map(resourceBooking,ResourceBookingDTO.class))
                .collect(Collectors.toList());
    }

    public  List<ResourceBookingDTO> findUserResourceBookings(){
        var userResourceBookings = resourceBookingDAO.findAll();
        return null;
    }

    public List<ResourceBookingDTO> getBookingsByWebUser(WebUserDTO webUser){
        List<ResourceBookingDTO> bookings = new ArrayList();
        for(ResourceBooking resourceBooking : resourceBookingDAO.findAll()){
            if(webUser.getUserName().equals(resourceBooking.getWebUser().getUserName())){
                bookings.add(modelMapper.map(resourceBooking,ResourceBookingDTO.class));
            }
        }

        return bookings;
    }


    public double calculatePaymentAmount(ResourceBookingDTO resourceBookingDTO) throws InvalidMinTimeException {
        if(Duration.between(resourceBookingDTO.getBookingStartDate(), resourceBookingDTO.getBookingEndDate()).toHours()< (resourceBookingDTO.getPropertyResource().getResourceMinTimeHrs())){
            throw new InvalidMinTimeException("El tiempo solicitado es menor al tiempo minimo");
        }else{
            return (Duration
                    .between(resourceBookingDTO
                            .getBookingStartDate(), resourceBookingDTO
                            .getBookingEndDate())
                    .toHours()) * resourceBookingDTO
                    .getPropertyResource()
                    .getResourceMinPrice();
        }
    }

    public ResourceBookingDTO updateResourceBooking(ResourceBookingDTO resourceBookingDTO) {
        return modelMapper
                .map(resourceBookingDAO
                        .update(modelMapper
                                .map(resourceBookingDTO, ResourceBooking.class)),ResourceBookingDTO.class);
    }


    public void sendEmail(ResourceBookingDTO resourceBookingDTO){
        EmailSender emailSender = new EmailSender();
        String to = resourceBookingDTO.getWebUserDTO().getUserEmail();
        String subject = "Confirmación de Reserva";
        String content = "Estimado/a " + resourceBookingDTO.getWebUserDTO().getUserName() + ",\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que su reserva para el recurso " +  resourceBookingDTO.getPropertyResource().getResource().getResourceType() + " ha sido confirmada.\n\n" +
                "Detalles de la reserva:\n\n" +
                "  Fecha y Hora de Inicio: " +  resourceBookingDTO.getBookingStartDate() + "\n" +
                "  Fecha y Hora de Fin: " + resourceBookingDTO.getBookingEndDate() + "\n" +
                "  Costo: " + resourceBookingDTO.getBookingCost() + "COP"+ "\n\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "Equipo de Soporte de [Living Corp :)]";

        emailSender.sendEmail(to, subject, content);
    }



}
