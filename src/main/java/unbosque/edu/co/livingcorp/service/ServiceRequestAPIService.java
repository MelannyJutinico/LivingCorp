package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.model.dto.ServiceRequestDTO;
import unbosque.edu.co.livingcorp.model.entity.ServiceRequest;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;

import java.io.Serializable;

@Stateless
public class ServiceRequestAPIService implements Serializable {

    @Inject
    private InterfaceDAO<ServiceRequest, Integer> serviceRequestDAO;
    private ModelMapper modelMapper = new ModelMapper();
    private final Client client;
    private final WebTarget baseTarget;

    public ServiceRequestAPIService() {
        this.client = ClientBuilder.newClient();
        this.baseTarget = client.target("http://localhost:8888/livingcorp/api/v1");
    }

    public ServiceRequestDTO createServiceRequest(ServiceRequestDTO serviceRequestDTO) throws ObjectAPICreateException {
        Response response = baseTarget
                .path("serviceRequest")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(serviceRequestDTO, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 200) {
            throw new ObjectAPICreateException("Solicitud no ha sido creada!");
        }

        return response.readEntity(ServiceRequestDTO.class);
    }

    public ServiceRequestDTO createRequest(ServiceRequestDTO serviceRequestDTO){
        return modelMapper
                .map(serviceRequestDAO
                        .save(modelMapper
                                .map(serviceRequestDTO, ServiceRequest.class)), ServiceRequestDTO.class);
    }




}
