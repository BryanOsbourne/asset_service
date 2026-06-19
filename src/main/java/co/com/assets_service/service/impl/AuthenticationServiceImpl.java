package co.com.assets_service.service.impl;

import lombok.RequiredArgsConstructor;
import co.com.assets_service.model.User;
import org.springframework.stereotype.Service;
import co.com.assets_service.mapper.UserMapper;
import co.com.assets_service.security.JwtService;
import co.com.assets_service.service.UserService;
import co.com.assets_service.dto.UserBasicResponseDTO;
import co.com.assets_service.service.AuthenticationService;
import co.com.assets_service.security.AuthenticationRequest;
import co.com.assets_service.security.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        User user = userService.findByUsername(authenticationRequest.getUsername());
        String token = jwtService.buildToken(user);
        UserBasicResponseDTO userBasicResponseDTO = userMapper.entityToBasicResponseDTO(user);
        return AuthenticationResponse.builder()
                .token(token)
                .user(userBasicResponseDTO)
                .build();

    }

}