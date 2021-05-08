package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.MatchRecordDao;
import cz.muni.fi.pa165.esports.dao.UserDao;
import cz.muni.fi.pa165.esports.entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Elena Álvarez
 *
 *  Implementation of the {@link UserServiceImpl}. This class is part of the
 *  service module of the application that provides the implementation of the
 *  business logic (main logic of the application).
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Inject
    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    //Persistence
    @Override
    public User create(User user, String password) {
        if (userDao.findByEmail(user.getEmail() ) == null) {
            //Put password as a hash
            String passwordHash = passwordEncoder.encode(password);
            user.setPasswordHash(passwordHash);
            userDao.create(user);
        }

       return user;
    }

    @Override
    public void delete(User user) {

    }

    //Getters
    @Override
    public List<User> findAll() {return userDao.findAll();}

    @Override
    public User findById(Long id) {return userDao.findById(id);}

    @Override
    public User findByUsername(String username) { return userDao.findByUsername(username); }

    @Override
    public User findByEmail(String email) { return userDao.findByEmail(email); }


    //Check
    @Override
    public boolean isAuthenticated(User user, String password) {
        return passwordEncoder.matches(password, user.getPasswordHash());
    }

    @Override
    public boolean isAdmin(Long id) {
        User userBd = userDao.findById(id);
        if (userBd != null)
            return userBd.isAdmin();
        return false;
    }
}
