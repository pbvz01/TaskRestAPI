package ua.zakharov.restapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.zakharov.restapplication.aop.exception_handling.exception.UserNotFoundException;
import ua.zakharov.restapplication.model.User;
import ua.zakharov.restapplication.repository.UserRepository;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        User user = userRepository.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return userRepository.findUserById(id);
    }
}
