package org.auth.service;

import org.auth.entities.RefreshToken;
import org.auth.entities.UserInfo;
import org.auth.repository.RefreshTokenRepository;
import org.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.tokens.Token;

import java.time.Instant;
import java.util.UUID;

public class RefreshTokenService {
@Autowired  RefreshTokenRepository refreshTokenRepository;
@Autowired UserRepository userRepository;

public RefreshToken createRefreshToken(String  usrname){
UserInfo extractedUserInfo=userRepository.findByUsername(usrname);
RefreshToken refreshToken= RefreshToken
        .builder()
        .userInfo(extractedUserInfo)
        .token(UUID.randomUUID().toString())
        .expiryDate(Instant.now()
                .plusMillis(600000)).build();
 return refreshTokenRepository.save(refreshToken);

}
public RefreshToken verifyExpiration(RefreshToken refreshToken){
    if (refreshToken.getExpiryDate().compareTo(Instant.now())<0){
        refreshTokenRepository.delete(refreshToken);
        throw new RuntimeException(refreshToken.getToken()+ " Refresh token is expired please make new login..!");
    }
    return refreshToken;
}

}
