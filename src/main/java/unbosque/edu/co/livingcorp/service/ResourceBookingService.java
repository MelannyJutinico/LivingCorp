package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.model.dto.ResourceBookingDTO;
import unbosque.edu.co.livingcorp.model.entity.ResourceBooking;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ResourceBookingService implements Serializable {

    @Inject
    private InterfaceDAO<ResourceBooking, Integer> resourceBookingDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public ResourceBookingDTO saveResourceBooking(ResourceBookingDTO resourceBookingDTO) {
        return modelMapper.map(resourceBookingDAO.save(modelMapper.map(resourceBookingDTO, ResourceBooking.class)),ResourceBookingDTO.class);
    }

    public void deleteResourceBooking(ResourceBookingDTO resourceBookingDTO) {
        resourceBookingDAO.delete(modelMapper.map(resourceBookingDTO, ResourceBooking.class));
    }

    public List<ResourceBookingDTO> findAllResourceBookings() {
        return resourceBookingDAO.findAll()
                .stream()
                .map(resourceBooking -> modelMapper.map(resourceBooking,ResourceBookingDTO.class))
                .collect(Collectors.toList());
    }

}
