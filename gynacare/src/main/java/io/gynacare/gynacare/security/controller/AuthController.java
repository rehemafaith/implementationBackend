package io.gynacare.gynacare.security.controller;


import io.gynacare.gynacare.model.JwtRequest;
import io.gynacare.gynacare.model.JwtResponse;
import io.gynacare.gynacare.security.model.dto.RegisterUserDto;
import io.gynacare.gynacare.security.service.AuthService;
import io.gynacare.gynacare.security.service.JwtUserDetailsService;
import io.gynacare.gynacare.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import java.sql.SQLException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final AuthService authService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        log.info("Get user details by username");
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody @Validated RegisterUserDto userDto) throws SQLException {
        return ResponseEntity.ok(authService.register(userDto));
    }

    private void authenticate(String username, String password) throws Exception {
        log.info("Authenticating username and password");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.error("user disabled");
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.error("invalid credential");
            throw new CredentialException("INVALID_CREDENTIALS");
        }
    }
}
