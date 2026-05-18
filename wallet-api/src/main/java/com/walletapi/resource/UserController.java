package com.walletapi.resource;


import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walletapi.dtos.UserDto;
import com.walletapi.entity.Role;
import com.walletapi.entity.User;
import com.walletapi.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Autowired
    private ModelMapper mapper;   

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> listarUsuarios(){
                return ResponseEntity.ok().body(userService.listUsers());
    }
/*     @GET
    public Response listarUsuarios(@QueryParam("page") @DefaultValue("0")Integer page,
        @QueryParam("pageSize") @DefaultValue("10")Integer pageSize){
            var users = userService.listUsers(page, pageSize);
        return Response.ok().body(userService.listUsers());
    }
 */

/*     @GET
    @Path("/getUser/{username}")
    @PreAuthorize("hasAnyRole('admin')")
    public User consultar(@PathVariable("username") String username){
        return userService.getUser(username);
    } */
    
     @GetMapping(path = "/getUser/{username}")
    public User consultar(@PathVariable("username") String username){
        return userService.getUser(username);
    }


    @PostMapping(path = "/save-user/")
    public ResponseEntity salvar(@RequestBody UserDto userDto){
        userService.saveUser(mapper.map(userDto, User.class));
        Optional<User> profissionais = userService.findById(userDto.getId_user());
        return ResponseEntity.ok(profissionais.map(e->mapper.map(e,
                User.class)).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build()));
    }

    @PutMapping(path = "/edit-user")
    public User editar(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping(path = "/delete-user/{id}")
    public void excluir(@PathVariable("id") Long id){
        userService.removeUser(id);
    }

   /*  @PostMapping(path = "/save-role")
    public ResponseEntity<Role> criarCargo(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save-role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    } */

    @PostMapping(path = "/role-addtouser")
    public ResponseEntity<?> adicionarCargo(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
/*
  Não atualiza mesmo com PutMapping
    @PutMapping(path = "/edit-roletouser/{username},{rolename}")
    public ResponseEntity<User> editarCargo(@PathVariable("username") final String username,
                                            @PathVariable("rolename") final String rolename){
        log.info("Editando role {} para usuario {}", rolename, username);
        userService.addRoleToUser(username, rolename);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping(path = "/token-refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 30 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
             }catch (Exception exception){
                response.setHeader("error in UserController ",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
@AllArgsConstructor
@RequiredArgsConstructor
class RoleToUserForm{
    private String username;
    private String roleName;

    
}