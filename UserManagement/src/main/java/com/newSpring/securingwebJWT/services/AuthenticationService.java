package com.newSpring.securingwebJWT.services;

import com.newSpring.securingwebJWT.dto.*;

public interface AuthenticationService {

    UserDTO signup(SignUpRequest signUpRequest);

    String signin(SignInRequest signIn);

    UserDTO getUserDetails(String authKey);

    boolean logout(String authKey);

}
