package com.walletapi.fiter;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/cos")
public class COSResource {

    @GET
    @Path("/data")
    @RolesAllowed("user")
    public Response getData() {
        // Simulate accessing data from COS (Cloud Object Storage)
        String data = "Secured Cloud Object Storage data!";
        return Response.ok(data).build();
    }
}
