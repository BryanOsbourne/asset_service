package co.com.assets_service.service;

import co.com.assets_service.security.AuthenticationRequest;
import co.com.assets_service.security.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);
}