package com.walletapi.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.entity.ItensDaVenda;

@ApplicationScoped
public class ItensDaVendaRepository implements PanacheRepositoryBase<ItensDaVenda, Integer> {

    @Query(value = " Select new com.walletquarkus.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas ", nativeQuery = true)
    public List<ItensDaVendaDto> findAllItens() {
        return null;
    }

    @Query(value = " Select new com.walletquarkus.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas and v.idVenda = ?1 ", nativeQuery = true)
    public List<ItensDaVendaDto> findItensVdById(Integer id) {
        return null;
    }

    @Query(value = " Select new com.walletquarkus.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda =  i.codevendas and v.dt_venda like %?1% ")
    public List<ItensDaVendaDto> litarItemDaVendaPorData(@Param("dt_venda") String dt_venda) {
        return null;
    }


    @Query(value = " Select new com.walletquarkus.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas and " +
            "STR_TO_DATE(v.dt_venda,'%d/%m/%y')  BETWEEN STR_TO_DATE(:dtIni,'%d/%m/%y') AND STR_TO_DATE(:dtFinal,'%d/%m/%y') ")
    public List<ItensDaVendaDto> litarItemDaVendaEntreData(@Param("dtIni") String dtIni, @Param("dtFinal") String dtFinal) {
        return null;
    }


    @Query(value = " Select new com.walletquarkus.dtos.ItensDaVendaDto(i.id_itens_vd,i.codevendas,i.cod_produtos," +
            " i.descricao, i.valor_compra, i.valor_venda, i.qtd_vendidas, i.valor_parcial, v.dt_venda )" +
            "from Vendas v inner join ItensDaVenda i " +
            "on v.codevenda = i.codevendas and v.nomeCliente = ?1")
    public List<ItensDaVendaDto> litarItemDaVendaPorCliente(@Param("nomeCliente") String nomeCliente) {
        return null;
    }


}

