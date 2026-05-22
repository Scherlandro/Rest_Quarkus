package com.walletapi.security;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/securityConfig")
public class SecurityConfig {

    @GET
    @Path("/dataBild")
    @RolesAllowed("user")
    public Response getDataBild() {
        // Simulate accessing data from COS (Cloud Object Storage)
        String data = "Secured Cloud Object Storage data!";
        return Response.ok(data).build();
    }
}
