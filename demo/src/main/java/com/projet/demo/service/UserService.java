package com.projet.demo.service;

import com.projet.demo.Entity.User;
import com.projet.demo.repository.UserRepository;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;

@Component
@Path("/user")
public class UserService {

    @Autowired
    private UserRepository ur;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getUsers() {
        List<User> users = ur.findAll();
        return Response.ok(users).build();
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(User user) {

        if (ur.findByEmail(user.getEmail()) != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email already exists")
                    .build();
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        ur.save(user);
        return Response.ok("User registered successfully").build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {

        User existingUser = ur.findByEmail(user.getEmail());
        if (existingUser == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid email or password")
                    .build();
        }


        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid email or password")
                    .build();
        }

        return Response.ok("Login successful").build();
    }
}
