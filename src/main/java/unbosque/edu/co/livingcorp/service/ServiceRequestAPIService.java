package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.model.dto.ServiceRequestDTO;
import unbosque.edu.co.livingcorp.model.entity.ServiceRequest;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import unbosque.edu.co.livingcorp.model.sender.EmailSender;

import java.io.Serializable;

@Stateless
public class ServiceRequestAPIService implements Serializable {

    @Inject
    private InterfaceDAO<ServiceRequest, Integer> serviceRequestDAO;
    private ModelMapper modelMapper = new ModelMapper();
    private final Client client;
    private final WebTarget baseTarget;
    private static final Logger logger = LogManager.getLogger(ServiceRequestAPIService.class);

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
        logger.info("Creando una solicitud y enviando correo...");
        sendEmail(serviceRequestDTO);
        return modelMapper
                .map(serviceRequestDAO
                        .save(modelMapper
                                .map(serviceRequestDTO, ServiceRequest.class)), ServiceRequestDTO.class);
    }


    public void sendEmail(ServiceRequestDTO serviceRequestDTO){
        EmailSender emailSender = new EmailSender();
        String to = serviceRequestDTO.getUser().getUserEmail();
        String subject = "Solicitud de servicio";
        String content = "Estimado/a " + serviceRequestDTO.getUser().getUserName() + ",\n\n" +
                "Esperamos que este mensaje le encuentre bien.\n\n" +
                "Nos complace informarle que la solicitud del servicio se ha recibido con exito.\n\n  " +
                "Detalles de la solicitud:\n\n" +
                "  Fecha y Hora de Inicio: " + serviceRequestDTO.getServiceDateTime()  + "\n" +
                "  Tipo: "+ serviceRequestDTO.getServiceProvider().getServiceType() + "\n" +
                "  Contacto: "+ serviceRequestDTO.getServiceProvider().getProviderEmail() + "\n" +
                "  Precio: "+ serviceRequestDTO.getServiceProvider().getServicePrice() + " COP" + "\n" +
                "  Propiedad: "+ serviceRequestDTO.getProperty().getPropertyName() + "\n" +
                "  Descripción: " + serviceRequestDTO.getRequestDescription() + "\n" +
                "Gracias por confiar en nuestro servicio.\n\n" +
                "Atentamente,\n\n" +
                "Equipo de Soporte de [Living Corp :)]";

        emailSender.sendEmail(to, subject, content);

    }



}
