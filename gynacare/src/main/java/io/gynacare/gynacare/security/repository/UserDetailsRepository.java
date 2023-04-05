package io.gynacare.gynacare.security.repository;

import io.gynacare.gynacare.security.model.entity.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDetailsRepository {
    private final DataSource dataSource;
    PreparedStatement cst = null;
    Connection conn = null;

    public UserDetails findUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM gynacare.APP_USER WHERE USERNAME='" + username + "'";

        conn = dataSource.getConnection();
        cst = conn.prepareStatement(query);
        ResultSet resultSet = cst.executeQuery();

        AppUser appUser;

        if (resultSet.next()) {
            appUser = AppUser.builder()
                    .id(resultSet.getBigDecimal("id"))
                    .roles(resultSet.getString("roles"))
                    .password(resultSet.getString("password"))
                    .username(resultSet.getString("username"))
                    .emailVerificationCode(resultSet.getString("email_verification_code"))
                    .isCredentialsExpired(resultSet.getString("is_credentials_expired"))
                    .isAccountLocked(resultSet.getString("is_account_locked"))
                    .isAccountEnabled(resultSet.getString("is_account_enabled"))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        this.conn.close();
        return appUser;
    }

    public Boolean save(AppUser appUser) throws SQLException {
        String query = "INSERT INTO APP_USER(username, password, roles) VALUES ('" + appUser.getUsername() + "','" + appUser.getPassword() + "','" + appUser.getRoles() + "')";
        log.info("Query: " + query);
        conn = dataSource.getConnection();
        cst = conn.prepareStatement(query);
        return cst.execute();
    }
}
