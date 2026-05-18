package com.walletapi.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.walletapi.entity.Vendas;

@ApplicationScoped
public class VendasRepository implements PanacheRepositoryBase<Vendas, Integer> {

        /*
          @Query(value = "Select v.*, i.id_itens_vd, i.codevendas, i.cod_produtos,i.descricao, i.valor_compra," + 
         " i.valor_venda, i.valor_parcial, i.qtd_vendidas, v.dt_venda " +
         " from Vendas v inner join ItensDaVenda i " +
         " on v.codevenda = i.codevendas " , nativeQuery = true)
    List<Vendas> findAllVendas();
         */

    @Query(value = "Select v.* from Vendas v ", nativeQuery = true)
    public List<Vendas> findAllVendas() {
        return null;
    }


    @Query(value = "Select v.id_venda, v.codevenda, v.id_cliente, v.nome_cliente, v.id_funcionario, v.nome_funcionario," +
            " v.dt_venda, v.subtotal, v.desconto, v.totalgeral, v.forma_de_pagamento, v.numero_de_parcelas, i.* " +
            " from Vendas v inner join (select it.id_itens_vd, it.codevendas, it.cod_produtos,it.descricao, it.valor_compra," +
            " it.valor_venda, it.valor_parcial, it.qtd_vendidas from ItensDaVenda it) as i " +
            " on v.codevenda = i.codevendas and v.nome_cliente like ?1% ", nativeQuery = true)
    public List<Vendas> findVendasByNomeDoCliente(@Param("nome_cliente") String nome_cliente) {
        return null;
    }


    @Query("Select new com.walletquarkus.dtos.VendasDto( v.idVenda, v.codevenda, v.idCliente, v.nomeCliente,  " +
            " v.idFuncionario, v.nomeFuncionario, v.dt_venda," +
            " v.subtotal, v.desconto, v.totalgeral, v.formasDePagamento, v.qtdDeParcelas, v.itensVd)" +
            "from Vendas v where v.nomeCliente = ?1 ")
    List<Vendas> findVendasByNomeCliente(@Param("nomeCliente") String nomeCliente) {
        return null;
    }

}
