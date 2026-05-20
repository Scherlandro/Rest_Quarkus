package com.walletapi.resource;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

// Substitutos Jakarta REST para o Spring Web
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.Context;

// Injeção de dependência padrão do CDI (Quarkus)
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletapi.dtos.UserDto;
import com.walletapi.entity.Role;
import com.walletapi.entity.User;
import com.walletapi.service.UserService;

// No Quarkus RESTEasy Reactive, usamos o Vert.x HttpServerRequest para manipular requests puros se necessário,
// mas para o token-refresh mantivemos a compatibilidade padrão do Jakarta.
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;


@Path("/api/user") // Equivalente ao @RequestMapping
@ApplicationScoped // Escopo do CDI equivalente ao componente gerenciado do Spring
@Consumes(MediaType.APPLICATION_JSON) // Define que todos os endpoints aceitam JSON
@Produces(MediaType.APPLICATION_JSON) // Define que todos os endpoints retornam JSON
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Inject // Substitui o @Autowired do Spring
    ModelMapper mapper;

    @GET
    @Path("/all")
    public Response listarUsuarios() {
        return Response.ok(userService.listUsers()).build();
    }

    @GET
    @Path("/getUser/{username}")
    public Response consultar(@PathParam("username") String username) {
        User user = userService.getUser(username);
        if (user == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Path("/save-user")
    public Response salvar(UserDto userDto) {
        userService.saveUser(mapper.map(userDto, User.class));
        Optional<User> profissionais = userService.findById(userDto.getId_user());

        return profissionais
                .map(e -> mapper.map(e, User.class))
                .map(record -> Response.ok(record).build())
                .orElse(Response.status(Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/edit-user")
    public User editar(User user) {
        return userService.saveUser(user);
    }

    @DELETE
    @Path("/delete-user/{id}")
    public Response excluir(@PathParam("id") Long id) {
        userService.removeUser(id);
        return Response.noContent().build(); // Melhor prática retornar 24 No Content para Delete
    }

   /* @POST
    @Path("/role-addtouser")
    public Response adicionarCargo(RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return Response.ok().build();
    }
*/
    @GET
    @Path("/token-refresh")
    public void refreshToken(@Context HttpServerRequest request, @Context HttpServerResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                        .withIssuer(request.absoluteURI())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);

                response.putHeader("Content-Type", "application/json");
                response.end(new ObjectMapper().writeValueAsString(tokens));
            } catch (Exception exception) {
                response.putHeader("error in UserController ", exception.getMessage());
                response.setStatusCode(403);

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.putHeader("Content-Type", "application/json");
                response.end(new ObjectMapper().writeValueAsString(error));
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

