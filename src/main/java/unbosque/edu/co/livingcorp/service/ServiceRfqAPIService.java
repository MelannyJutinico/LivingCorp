package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.ObjectNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.exception.ObjectAPINotFoundException;
import unbosque.edu.co.livingcorp.model.dto.ServiceRFQDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;


import java.io.Serializable;
import java.util.List;

@Stateless
public class ServiceRfqAPIService implements Serializable {

    private final Client client;
    private final WebTarget baseTarget;

    public ServiceRfqAPIService() {
        this.client = ClientBuilder.newClient();
        this.baseTarget = client.target("/livingcorp/api/v1");
    }

    public ServiceRFQDTO createServiceRFQ(ServiceRFQDTO serviceRFQDTO) throws ObjectAPICreateException {
        Response response = baseTarget
                .path("/rfq")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(serviceRFQDTO, MediaType.APPLICATION_JSON_TYPE));

        if(response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            throw new ObjectAPICreateException("La cotización no ha sido creada!");
        }

        return response.readEntity(ServiceRFQDTO.class);
    }

    public List<ServiceRFQDTO> readAllServiceRFQs(){
        List<ServiceRFQDTO> servicesRFQs = baseTarget
                .path("/rfq")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ServiceRFQDTO>>() {});
        return servicesRFQs;
    }

    public ServiceRFQDTO getServiceRFQById(int id) throws ObjectAPINotFoundException {
        Response response = baseTarget
                .path("/rfq" + "/" + id)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        if(response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new ObjectAPINotFoundException("No se ha logrado encontrar la cotización que busca");
        }
        return response.readEntity(ServiceRFQDTO.class);
    }

    public ServiceRFQDTO getServiceRFQByUser(WebUserDTO webUserDTO){
        Response response = baseTarget
                .queryParam("webUserDTO", webUserDTO)
                .path("")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();
        return null;
    }




}
