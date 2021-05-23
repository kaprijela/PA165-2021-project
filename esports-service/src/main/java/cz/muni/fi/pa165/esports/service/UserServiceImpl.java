package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.UserDao;
import cz.muni.fi.pa165.esports.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Elena Álvarez
 * <p>
 * Implementation of the {@link UserServiceImpl}. This class is part of the
 * service module of the application that provides the implementation of the
 * business logic (main logic of the application).
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Persistence
    @Override
    public SystemUser create(SystemUser systemUser, String password) {
        //Put password as a hash
        String passwordHash = passwordEncoder.encode(password);
        systemUser.setPasswordHash(passwordHash);
        userDao.create(systemUser);

        return systemUser;
    }

    @Override
    public void delete(SystemUser systemUser) {

    }

    //Getters
    @Override
    public List<SystemUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public SystemUser findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public SystemUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public SystemUser findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    //Check
    @Override
    public boolean isAuthenticated(SystemUser systemUser, String password) {
        return passwordEncoder.matches(password, systemUser.getPasswordHash());
    }

    @Override
    public boolean isAdmin(Long id) {
        SystemUser systemUserBd = userDao.findById(id);
        if (systemUserBd != null)
            return systemUserBd.isAdmin();
        return false;
    }
}
