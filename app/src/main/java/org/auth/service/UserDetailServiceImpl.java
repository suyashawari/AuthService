package org.auth.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.auth.entities.RefreshToken;
import org.auth.entities.UserInfo;
import org.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
UserInfo user =userRepository.findByUsername(username);
       if (user==null){
           throw new UsernameNotFoundException("could not found User...!");
       }
        return new CustomUserDetails(user);
    }

}
