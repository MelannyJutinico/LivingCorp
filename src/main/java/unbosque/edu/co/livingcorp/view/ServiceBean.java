package unbosque.edu.co.livingcorp.view;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import unbosque.edu.co.livingcorp.exception.ObjectAPICreateException;
import unbosque.edu.co.livingcorp.model.dto.ServiceRFQDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.service.ServiceRfqAPIService;

import java.io.Serializable;
import java.time.LocalDateTime;

@Named
@RequestScoped
public class ServiceBean implements Serializable {

    @Inject
    private ServiceRfqAPIService serviceRfqAPIService;
    private ServiceRFQDTO serviceRfqDTO;

    @PostConstruct
    public void init() {
        serviceRfqDTO = new ServiceRFQDTO();
    }

    public void saveServiceRfq() {


        var webUserDTO = (WebUserDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        serviceRfqDTO.setRfqDateTime(LocalDateTime.now());
        serviceRfqDTO.setUser(webUserDTO);

        try {
            serviceRfqAPIService.createServiceRFQ(null);
        } catch (ObjectAPICreateException e) {
            throw new RuntimeException(e);
        }
    }








}
