package com.walletapi.mapper;
import com.walletapi.dtos.ProdutoDto;
import com.walletapi.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi") // "jakarta" faz com que ele seja um CDI Bean injetável
public interface ProdutoMapper {

    ProdutoDto toDto(Produto produto);

    @Mapping(source = "dtCadastro", target = "dtCadastro", dateFormat = "dd/MM/yyyy")
    Produto toEntity(ProdutoDto dto);

    // Útil para o método Editar (Update)
    @Mapping(target = "idProduto", ignore = true) // Protege o ID de ser alterado
    void updateEntityFromDto(ProdutoDto dto, @MappingTarget Produto entity);
}
