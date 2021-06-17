package com.example.demo.userlogin;

import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
@Log4j2
@PersistenceContext

@Service
@AllArgsConstructor
public class UserLoginService implements UserDetailsService {
    private EntityManager em;
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserLoginRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public Boolean checkEmail (String email){

        boolean userExists = appUserRepository
                .findByEmail(email)
                .isPresent();
        return !userExists;
    }
    public String getToken(UserLogin userLogin){
        Random random = new Random();
        Integer token = random.nextInt(9000)+1000;
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token.toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                userLogin
        );
        log.info("ssss",confirmationToken.toString());
        log.info("ssss",userLogin.toString());

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token.toString();
    }
    public String signUpUser(Optional<UserLogin> userLogin) {
        if (!userLogin.isPresent())
            throw new IllegalStateException("email not valid");


        String encodedPassword = bCryptPasswordEncoder
                .encode(userLogin.get().getPassword());

        userLogin.get().setPassword(encodedPassword);

        appUserRepository.save(userLogin.get());

        return "sign up success";

    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
    public int setPassAppUser(String email,String pass){
         appUserRepository.setPassAppUser(email,pass);
         em.clear();
         return 0;
    }
}
