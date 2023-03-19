package io.gynacare.gynacare.security.model.dto;

import io.gynacare.gynacare.security.enums.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private RolesEnum role;
    private Date expiry;
}
