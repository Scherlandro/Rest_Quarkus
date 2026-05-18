package com.walletapi.resource;

import com.walletapi.entity.Produto;
import com.walletapi.dtos.ProdutoDto;
import com.walletapi.mapper.ProdutoMapper;
import com.walletapi.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @Inject
    ProdutoMapper mapper;

    @GET
    @Path("/all")
    public List<ProdutoDto> listar() {
        return service.listarProduto().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Integer id) {
        return service.findById(id)
                .map(this::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("/salvar")
    @Transactional
    public Response salvar(ProdutoDto dto) {
        Produto p = mapper.toEntity(dto);
        service.save(p);
        return Response.status(201).entity(mapper.toDto(p)).build();
    }

    @PUT
    @Path("/editar/{id}")
    @Transactional
    public Response editar(@PathParam("id") Integer id, ProdutoDto dto) {
        return service.findById(id)
                .map(entity -> {
                    mapper.updateEntityFromDto(dto, entity);
                    // Como está em uma transação, o Hibernate salvará as alterações automaticamente
                    return Response.ok(mapper.toDto(entity)).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/delete/{id}")
    public void excluir(@PathParam("id") Integer id) {
        service.delete(id);
    }

    // Métodos auxiliares de mapeamento (Substituindo ModelMapper manualmente para este exemplo)
    private ProdutoDto toDto(Produto p) {
        // Implemente a conversão ou use MapStruct
        return new ProdutoDto();
    }

    private Produto toEntity(ProdutoDto dto) {
        // Implemente a conversão
        return new Produto();
    }

}