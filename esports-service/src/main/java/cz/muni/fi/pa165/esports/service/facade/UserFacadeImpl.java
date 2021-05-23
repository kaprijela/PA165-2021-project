package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.AuthenticatedUserDTO;
import cz.muni.fi.pa165.esports.dto.UserDTO;
import cz.muni.fi.pa165.esports.entity.SystemUser;
import cz.muni.fi.pa165.esports.facade.UserFacade;
import cz.muni.fi.pa165.esports.service.BeanMappingService;
import cz.muni.fi.pa165.esports.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.ValidationException;
import java.util.List;

/**
 * @author Elena Álvarez
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private UserService userService;

    @Override
    public Long registerNewUser(UserDTO user, String password) throws ValidationException {
        SystemUser u = userService.create(beanMappingService.mapTo(user, SystemUser.class), password);
        return u.getId();

    }

    @Override
    public void removeUser(Long id) {
        SystemUser systemUser = userService.findById(id);
        userService.delete(beanMappingService.mapTo(systemUser, SystemUser.class));
    }

    @Override
    public boolean isAuthenticated(AuthenticatedUserDTO authenticatedUser) {
        SystemUser systemUser = userService.findByUsername(authenticatedUser.getUsername());
        if (systemUser == null) return false;
        return userService.isAuthenticated(systemUser, authenticatedUser.getPassword());
    }

    @Override
    public boolean isAdmin(UserDTO userDTO) {
        SystemUser systemUser = userService.findById(userDTO.getId());
        if (systemUser == null) return false;
        return userService.isAdmin(systemUser.getId());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {
        SystemUser systemUser = userService.findById(id);
        return (systemUser == null) ? null : beanMappingService.mapTo(systemUser, UserDTO.class);
    }

    @Override
    public UserDTO findByUsername(String username) {
        SystemUser systemUser = userService.findByUsername(username);
        return (systemUser == null) ? null : beanMappingService.mapTo(systemUser, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        SystemUser systemUser = userService.findByEmail(email);
        return (systemUser == null) ? null : beanMappingService.mapTo(systemUser, UserDTO.class);
    }
}
