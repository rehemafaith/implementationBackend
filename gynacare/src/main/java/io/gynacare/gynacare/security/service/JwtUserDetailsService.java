package io.gynacare.gynacare.security.service;

import io.gynacare.gynacare.security.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserDetailsRepository userDetailsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return userDetailsRepository.findUserByUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(e.getMessage());
		}
	}
    /*
    private final CustomUserDetailsService customUserDetailService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("rehema".equals(username)) {
//			return new User("rehema", "$2a$12$fgXLiuvRbCTPGVKoj.ow6upWd3YOu4evrLXAk1qvSr08p/8nxWZZa",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
        return customUserDetailService.loadUserByUsername(username);
    }*/
}
