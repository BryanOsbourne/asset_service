package co.com.assets_service.service.impl;

import co.com.assets_service.model.User;
import org.springframework.stereotype.Service;
import co.com.assets_service.service.UserService;
import co.com.assets_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    @Override
    public User findByUsernameWithAuthorities(String username) {
        return userRepository.findByUsernameWithAuthorities(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

}