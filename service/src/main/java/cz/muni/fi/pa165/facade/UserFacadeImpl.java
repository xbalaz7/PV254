package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.MappingService;
import cz.muni.fi.pa165.UserService;
import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Radovan Lapar
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Inject
    private MappingService mappingService;

    @Inject
    private UserService userService;

    @Override
    public void add(UserDTO user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        if (password == null) {
            throw new IllegalArgumentException("Password is null");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password is an empty string");
        }
        User mapped = mappingService.mapTo(user, User.class);
        userService.registerUser(mapped, password);
    }

    @Override
    public void remove(UserDTO user) {
        userService.remove(mappingService.mapTo(user, User.class));
    }

    @Override
    public void update(UserDTO user) {
        userService.update(mappingService.mapTo(user, User.class));
    }

    @Override
    public List<UserDTO> findAll() {
        return mappingService.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return null;
        }
        return mappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return null;
        }
        return mappingService.mapTo(user, UserDTO.class);
    }

    @Override
    public Boolean isAdmin(UserDTO user) {
        return userService.isAdmin(mappingService.mapTo(user, User.class));
    }

    @Override
    public UserDTO authenticate(UserAuthenticateDTO user) {
        User foundUser = userService.findByEmail(user.getEmail());
        if (foundUser == null) {
            return null;
        }
        if (userService.authenticate(foundUser, user.getPassword())) {
            return mappingService.mapTo(foundUser, UserDTO.class);
        }

        return null;
    }
}
