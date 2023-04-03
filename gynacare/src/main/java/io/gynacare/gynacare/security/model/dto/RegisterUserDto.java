package io.gynacare.gynacare.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDto {
    @NotNull(message = "Username is required to register")
    private String username;
    @NotNull(message = "Password is required to register")
    private String password;
    private List<String> roles = new ArrayList<>();
}
