package org.auth.util;

import lombok.Data;
import org.auth.model.UserInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.*;

@Component
@Data
public class UserValidatorUtil {
    UserInfoDto userInfoDto;
    // Regex to check valid password.


    public  Boolean isEmailCorrect(String email) {
        if (email==null) return false;
        String regex = "^(.+)@(.+)$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }

    public  Boolean isPasswordCorrect(String password) {

        if (password == null) {
            return false;
        }
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher isCorrect = pattern.matcher(password);
        return isCorrect.matches();
    }
}
