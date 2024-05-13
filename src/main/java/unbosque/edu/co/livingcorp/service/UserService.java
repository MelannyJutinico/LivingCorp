package unbosque.edu.co.livingcorp.service;

import jakarta.ejb.Stateless;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import unbosque.edu.co.livingcorp.exception.UserAuthenticationException;
import unbosque.edu.co.livingcorp.exception.UserRegistrationException;
import unbosque.edu.co.livingcorp.model.dto.PropertyDTO;
import unbosque.edu.co.livingcorp.model.dto.WebUserDTO;
import unbosque.edu.co.livingcorp.model.entity.WebUser;
import unbosque.edu.co.livingcorp.model.persistence.InterfaceDAO;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class UserService implements Serializable {

    @Inject
    private InterfaceDAO<WebUser, String> userDAO;

    private final ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger = LogManager.getLogger(PropertyManagementService.class);


    public void registerUser(WebUserDTO user) throws UserRegistrationException, NoSuchAlgorithmException {
        Optional<WebUser> existingUser = Optional.ofNullable(userDAO.findById(user.getUserName()));
        logger.info("Comprobando si el usuario ya existe...");
        if (existingUser.isPresent()) {
            logger.info("Usuario ya existente...");
            throw new UserRegistrationException("El nombre de usuario ya está en uso.");
        }
        for (WebUser webUser : userDAO.findAll()) {
            if (user.getUserEmail().equals(webUser.getUserEmail())) {
                existingUser = Optional.of(webUser);
            }
        }
        if (existingUser.isPresent()) {
            logger.info("Correo electronico en uso");
            throw new UserRegistrationException("El correo electrónico ya está en uso.");
        }

        String encryptedPassword = encryptPassword(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        userDAO.save(modelMapper.map(user, WebUser.class));
        logger.info("Usuario registrado exitosamente...");
    }

    public static String encryptPassword(String password) throws NoSuchAlgorithmException {
        // Crear una instancia del algoritmo de cifrado SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        // Convertir la contraseña en bytes utilizando UTF-8
        byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        // Convertir los bytes cifrados a una representación hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public WebUserDTO authenticateUser(WebUserDTO userDTO) throws NoSuchAlgorithmException, UserAuthenticationException {
        String userName = userDTO.getUserName();
        String password = userDTO.getUserPassword();
        try {
            WebUser user = userDAO.findById(userName);
            if (user != null) {
                String encryptedPassword = encryptPassword(password);
                if (encryptedPassword.equals(user.getUserPassword())) {
                    user.setLastLogin(LocalDateTime.now());
                    userDAO.update(user);
                    WebUserDTO authenticatedUserDTO = modelMapper.map(user, WebUserDTO.class);
                    authenticatedUserDTO.setNumberLoginFailed(0);
                    authenticatedUserDTO.setLoginCorrect(true);
                    logger.info("Usuario "+authenticatedUserDTO.getUserName()+ " autenticado exitosamente...");
                    return authenticatedUserDTO;
                } else {
                    int loginAttempts = userDTO.getNumberLoginFailed() + 1;
                    userDTO.setNumberLoginFailed(loginAttempts);
                    if (loginAttempts >= 3) {
                        user.setBlocked(true);
                        userDAO.update(user);
                        logger.info("Bloqueando a usuario "+userDTO.getUserName());
                    }
                    userDTO.setLoginCorrect(false);
                    userDTO.setBlocked(user.isBlocked());

                    return userDTO;
                }
            } else {
                throw new UserAuthenticationException("Usuario no encontrado.");

            }
        } catch (NoResultException e) {
            logger.info("Usuario no autenticado..");
            throw new UserAuthenticationException(e.getMessage());

        }
    }

    public List<PropertyDTO> getUserProperties(WebUserDTO userDTO) {
        var userProperties = userDAO.findById(userDTO.getUserName()).getUserProperties();
        return userProperties
                .stream()
                .map(property -> modelMapper.map(property, PropertyDTO.class))
                .collect(Collectors.toList());
    }


    public ArrayList<String> getUserNames(String nameExcluded){

        ArrayList<WebUser> users = userDAO.findAll();
        ArrayList<String> userNames = new ArrayList<>();
        for(WebUser user : users) {
            if(!user.getUserName().equals(nameExcluded)){
                userNames.add(user.getUserName());
            }
        }

        return userNames;

    }

    public WebUserDTO getAnUser(String name){
        return modelMapper.map(userDAO.findById(name), WebUserDTO.class);
    }


}




