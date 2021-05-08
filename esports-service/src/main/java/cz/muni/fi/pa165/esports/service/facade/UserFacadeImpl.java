package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.AuthenticatedUserDTO;
import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.dto.UserDTO;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.User;
import cz.muni.fi.pa165.esports.facade.UserFacade;
import cz.muni.fi.pa165.esports.service.BeanMappingService;
import cz.muni.fi.pa165.esports.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;


import javax.inject.Inject;

/**
 @author Elena Álvarez
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
        return null;
    }

    @Override
    public void removeUser(Long id) {

    }

    @Override
    public boolean isAuthenticated(AuthenticatedUserDTO authenticatedUser) {
        User user = userService.findByUsername(authenticatedUser.getUsername());
        if(user == null) return false;
        return userService.isAuthenticated(user, authenticatedUser.getPassword());
    }

    @Override
    public boolean isAdmin(UserDTO userDTO) {
        User user = userService.findById(userDTO.getId());
        if(user == null) return false;
        return userService.isAdmin(user.getId());
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return beanMappingService.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userService.findById(id);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findByUsername(String username) {
        User user = userService.findByUsername(username);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userService.findByEmail(email);
        return (user == null) ? null : beanMappingService.mapTo(user, UserDTO.class);
    }
}
