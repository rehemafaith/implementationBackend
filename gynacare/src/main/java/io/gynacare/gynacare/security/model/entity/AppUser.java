package io.gynacare.gynacare.security.model.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Stream;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "APP_USER")
public class AppUser implements UserDetails {

    private static final long serialVersionUID = -6031797646032425241L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private BigDecimal id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "IS_ACCOUNT_ENABLED", length = 1)
    private String isAccountEnabled;

    @Column(name = "EMAIL_VERIFICATION_CODE")
    private String emailVerificationCode;

    @Column(name = "IS_ACCOUNT_LOCKED", length = 1)
    private String isAccountLocked;

    @Column(name = "IS_ACCOUNT_EXPIRED", length = 1)
    private String isAccountExpired;

    @Column(name = "IS_CREDENTIALS_EXPIRED", length = 1)
    private String isCredentialsExpired;

    @Column(name = "ROLES")
    private String roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(roles.split(",")).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
