package com.ecommerce.service.login;

import com.ecommerce.dto.login.LoginRequestDTO;
import com.ecommerce.dto.login.LoginResponseDTO;
import com.ecommerce.dto.login.UserProfileResponseDTO;
import com.ecommerce.entity.refreshToken.RefreshToken;
import com.ecommerce.entity.registration.UserEntity;
import com.ecommerce.exception.registration.InvalidCredentialsException;
import com.ecommerce.security.CustomUserDetails;
import com.ecommerce.security.jwt.JwtService;
import com.ecommerce.service.refreshToken.RefreshTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public LoginServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

           try {
               Authentication authenticate = authenticationManager.authenticate(
                       new UsernamePasswordAuthenticationToken(
                               loginRequestDTO.getUsername(),
                               loginRequestDTO.getPassword()));

               CustomUserDetails principal =
                       (CustomUserDetails) authenticate.getPrincipal();

               String token = jwtService.generateToken(principal);

               UserEntity user = principal.getUserEntity();

               RefreshToken refreshToken =
                       refreshTokenService.createRefreshToken(user);

               LoginResponseDTO response = new LoginResponseDTO();

               response.setAccessToken(token);
               response.setRefreshToken(refreshToken.getToken());
               response.setExpiresIn(jwtService.getExpiration());
               response.setTokenType("Bearer");
               response.setUserId(user.getId());
               response.setFirstName(user.getFirstName());
               response.setLastName(user.getLastName());
               response.setEmailAddress(user.getEmailAddress());
               response.setRole(user.getRole());

               return response;
           }
           catch (BadCredentialsException |
                  UsernameNotFoundException e)
           {
               throw new InvalidCredentialsException(
                       "Invalid username or password"
               );
           }
    }

    @Override
    public UserProfileResponseDTO getCurrentUser(CustomUserDetails customUserDetails) {

        UserEntity user = customUserDetails.getUserEntity();

        return UserProfileResponseDTO.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .role(user.getRole())
                .build();
    }
}
