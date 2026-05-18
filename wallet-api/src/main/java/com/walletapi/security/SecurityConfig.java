package com.walletapi.security;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/* 
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;

import io.quarkus.security.Authenticated;
import io.quarkus.security.AuthenticationCompletionException;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import lombok.extern.slf4j.Slf4j;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT; */


@Path("/cos")
public class SecurityConfig {

    @GET
    @Path("/data")
    @RolesAllowed("user")
    public Response getData() {
        // Simulate accessing data from COS (Cloud Object Storage)
        String data = "Secured Cloud Object Storage data!";
        return Response.ok(data).build();
    }
}
