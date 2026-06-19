package co.com.assets_service.service;

import co.com.assets_service.model.User;

public interface UserService {
    User findByUsername(String username);
    User findByUsernameWithAuthorities(String username);
}
