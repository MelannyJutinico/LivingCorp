package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.ObjectNotFoundException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.exception.ObjectAPINotFoundException;
import unbosque.edu.co.livingcorp.model.dto.ServiceRFQDTO;
import unbosque.edu.co.livingcorp.model.dto.ServiceRequestDTO;
import unbosque.edu.co.livingcorp.model.entity.ServiceRFQ;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import unbosque.edu.co.livingcorp.model.sender.EmailSender;


import java.io.Serializable;
import java.util.List;

@Stateless
public class ServiceRfqAPIService implements Serializable {

    @Inject
    private InterfaceDAO<ServiceRFQ, Integer> serviceRfqDAO;
    private ModelMapper modelMapper = new ModelMapper();
    private final Client client;
    private final WebTarget baseTarget;
    private static final Logger logger = LogManager.getLogger(ServiceRfqAPIService.class);

    public ServiceRfqAPIService() {
        this.client = ClientBuilder.newClient();
        this.baseTarget = client.target("http://localhost:8888/livingcorp/api/v1");
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

    public ServiceRFQDTO createRFQ(ServiceRFQDTO serviceRFQDTO){
        logger.info("Creando el servicio RFQ y enviando correo...");
        sendEmail(serviceRFQDTO);
       return modelMapper
                .map(serviceRfqDAO
                        .save(modelMapper
                                .map(serviceRFQDTO, ServiceRFQ.class)),ServiceRFQDTO.class);
    }

    public void sendEmail(ServiceRFQDTO serviceRFQDTO){

        EmailSender emailSender = new EmailSender();
        String to = serviceRFQDTO.getUser().getUserEmail();
        String subject = "Confirmación de cotización";
        String content = "Estimado/a " + serviceRFQDTO.getUser().getUserName() + ",\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que su cotización se ha recibido con exito. \n\n" +
                "Detalles de la cotización:\n\n" +
                "  Fecha y Hora de la cotización: " +  serviceRFQDTO.getRfqDateTime() + "\n" +
                "  Propiedad: " + serviceRFQDTO.getProperty().getPropertyName() + "\n" +
                "  Tipo: "+ serviceRFQDTO.getServiceProvider().getServiceType() + "\n" +
                "  Contacto: "+ serviceRFQDTO.getServiceProvider().getProviderEmail() + "\n" +
                "  Precio: "+ serviceRFQDTO.getServiceProvider().getServicePrice() + " COP" + "\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "Equipo de Soporte de [Living Corp :)]";

        emailSender.sendEmail(to, subject, content);
    }


}
