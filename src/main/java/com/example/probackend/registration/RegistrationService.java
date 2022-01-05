package com.example.probackend.registration;

import com.example.probackend.Security.ApplicationUserRole;
import com.example.probackend.User.AppUser;
import com.example.probackend.User.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final EmailValidation emailValidation;
    private final AppUserService appUserService;

    public RegistrationService(EmailValidation emailValidation, AppUserService appUserService) {
        this.emailValidation = emailValidation;
        this.appUserService = appUserService;
    }

    public String register(RegistrationRequest request){
        boolean isValid =
                emailValidation.test(request.getEmail());
        if(!isValid){
            throw new IllegalStateException("Email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        ApplicationUserRole.USER
                )
        );
    }
}
