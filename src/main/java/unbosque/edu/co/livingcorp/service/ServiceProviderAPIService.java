package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import unbosque.edu.co.livingcorp.model.dto.ServiceProviderDTO;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ServiceProviderAPIService implements Serializable {

    private final Client client;
    private final WebTarget baseTarget;
    private static final Logger logger = LogManager.getLogger(ServiceRequestAPIService.class);

    public ServiceProviderAPIService() {
        this.client = ClientBuilder.newClient();
        this.baseTarget = client.target("http://localhost:8888/livingcorp/api/v1");
    }

    public List<ServiceProviderDTO> getAllServiceProviders() {
        Response response = null;
        List<ServiceProviderDTO> providers = null;

        try {
            response = baseTarget
                    .path("/provider")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                providers = response.readEntity(new GenericType<List<ServiceProviderDTO>>() {});
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }
        logger.info("Listando todos los provedores...");
        return providers;
    }

    public ServiceProviderDTO findServiceProviderById(Integer id) {
        Response response = null;
        ServiceProviderDTO provider = null;
        try {
            response = baseTarget
                    .path("/provider/" + id)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                provider = response.readEntity(ServiceProviderDTO.class);
            }
        } finally {
            if (response != null) {
                response.close();
            }

        }
        logger.info("Encontrando un provedor por id" + id);
        return provider;
    }

}
