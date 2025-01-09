package org.auth.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.auth.entities.UserInfo;
import org.auth.model.UserInfoDto;
import org.auth.repository.UserRepository;
import org.auth.util.UserValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Component
@Data
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("could not found User...!");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfAlreadyExist(UserInfoDto userInfoDto) {
        return userRepository.findByUsername(userInfoDto.getUserName());
    }



    public Boolean signupUser(UserInfoDto userInfoDto) {

         final UserValidatorUtil userValidatorUtil=new UserValidatorUtil();
       if ( !userValidatorUtil.isEmailCorrect(userInfoDto.getEmail()) || ! userValidatorUtil.isPasswordCorrect(userInfoDto.getPassword())){
           return false;
       }



        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if (Objects.nonNull(checkIfAlreadyExist(userInfoDto))){
            return false;
        }
        String userId= UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userId,userInfoDto.getUserName(),userInfoDto.getPassword(),new HashSet<>()));
        return true;
    }
}
