package unbosque.edu.co.livingcorp.service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

import java.io.Serializable;

public class ServiceProviderAPIService implements Serializable {

        private final Client client;
        private final WebTarget baseTarget;

        public ServiceProviderAPIService() {
            this.client = ClientBuilder.newClient();
            this.baseTarget = client.target("/livingcorp/api/v1");
        }






}
