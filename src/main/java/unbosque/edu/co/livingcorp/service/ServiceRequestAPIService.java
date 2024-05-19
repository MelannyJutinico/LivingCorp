package unbosque.edu.co.livingcorp.service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.model.dto.ServiceRequestDTO;

import java.io.Serializable;

public class ServiceRequestAPIService implements Serializable {

    private final Client client;
    private final WebTarget baseTarget;

    public ServiceRequestAPIService() {
        this.client = ClientBuilder.newClient();
        this.baseTarget = client.target("/livingcorp/api/v1");
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

}
