package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.InvalidMinTimeException;
import unbosque.edu.co.livingcorp.model.dto.ResourceBookingDTO;
import unbosque.edu.co.livingcorp.model.entity.ResourceBooking;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceBookingService implements Serializable {

    @Inject
    private InterfaceDAO<ResourceBooking, Integer> resourceBookingDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public ResourceBookingDTO saveResourceBooking(ResourceBookingDTO resourceBookingDTO) {
        return modelMapper
                .map(resourceBookingDAO
                        .save(modelMapper
                                .map(resourceBookingDTO, ResourceBooking.class)),ResourceBookingDTO.class);
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


    public double calculatePaymentAmount(ResourceBookingDTO resourceBookingDTO) throws InvalidMinTimeException {
        if(Duration.between(resourceBookingDTO.getBookingStartDate(), resourceBookingDTO.getBookingEndDate()).toHours()< (resourceBookingDTO.getPropertyResourceDTO().getResourceMinTimeHrs())){
            throw new InvalidMinTimeException("El tiempo solicitado es menor al tiempo minimo");
        }else{
            return ((Duration
                    .between(resourceBookingDTO
                            .getBookingStartDate(), resourceBookingDTO
                            .getBookingEndDate())
                    .toHours()))* resourceBookingDTO
                    .getPropertyResourceDTO()
                    .getResourceMinPrice();
        }
    }

    public ResourceBookingDTO updateResourceBooking(ResourceBookingDTO resourceBookingDTO) {
        return modelMapper
                .map(resourceBookingDAO
                        .update(modelMapper
                                .map(resourceBookingDTO, ResourceBooking.class)),ResourceBookingDTO.class);
    }

}
