package io.gynacare.gynacare.security.service;

import io.gynacare.gynacare.security.model.dto.RegisterUserDto;
import io.gynacare.gynacare.security.model.entity.AppUser;
import io.gynacare.gynacare.security.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDetailsRepository userDetailsRepository;
    private final BCryptPasswordEncoder encoder;

    public Boolean register(RegisterUserDto userDto) throws SQLException {
        StringBuilder roles = new StringBuilder();

        System.out.println("role: " + userDto.getRoles());
        userDto.getRoles().forEach(role -> roles.append(role).append(","));
        roles.deleteCharAt(roles.length() - 1);

        AppUser appUser = AppUser.builder()
                .roles(roles.toString())
                .username(userDto.getUsername())
                .password(encoder.encode(userDto.getPassword()))
                .build();
        return userDetailsRepository.save(appUser);
    }
}
