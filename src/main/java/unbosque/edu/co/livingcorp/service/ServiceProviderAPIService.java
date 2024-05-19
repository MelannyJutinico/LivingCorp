package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import unbosque.edu.co.livingcorp.model.dto.ServiceProviderDTO;

import java.io.Serializable;
import java.util.List;

@Stateless
public class ServiceProviderAPIService implements Serializable {

        private final Client client;
        private final WebTarget baseTarget;

        public ServiceProviderAPIService() {
            this.client = ClientBuilder.newClient();
            this.baseTarget = client.target("http://localhost:8888/livingcorp/api/v1");
        }


        public  List<ServiceProviderDTO > getAllServiceProviders() {
            List<ServiceProviderDTO > providers = baseTarget
                    .path("/provider")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(new GenericType<List<ServiceProviderDTO >>(){});
            return providers;
        }






}
