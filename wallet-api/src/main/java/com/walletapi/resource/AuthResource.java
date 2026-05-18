package com.walletapi.resource;

import java.util.HashSet;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthResource {

    @POST
    @Path("/login")
    public String login() {
        // Simulate a login process, e.g., validate user credentials (not shown here)

        // Roles can be dynamically set based on the authenticated user
        Set<String> roles = new HashSet<>();
        roles.add("user");  // Add roles as needed, e.g., "admin", "cos-access"

        // Generate a JWT token
        String token = Jwt
                .issuer("https://example.com/issuer")
                .subject("username")  // Use actual username
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 3600)  // Token expires in 1 hour
                .sign();

        return token;
    }
}
