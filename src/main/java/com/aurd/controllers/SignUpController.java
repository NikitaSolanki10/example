package com.aurd.controllers;


import com.aurd.entity.UserModal;
import com.aurd.repository.UserRepository;
import com.aurd.request.SignUpRequest;
import com.aurd.response.SignUpResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/signUp")
public class SignUpController {

    @Inject
    UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public SignUpResponse signUp(SignUpRequest request)
    {

        UserModal userModal=new UserModal();
        userModal.setEmail(request.getEmail());
        userModal.setName(request.getName());
        userModal.setPassword(request.getPassword());

        userRepository.persistAndFlush(userModal);

        SignUpResponse signUpResponse=new SignUpResponse();
        signUpResponse.setErrorCode(0);
        signUpResponse.setMessage("Sign Up Successfull");
        signUpResponse.setStatus(true);

        return  signUpResponse;
    }
}
