package com.walletapi.service;

import java.util.List;
import java.util.Optional;

import com.walletapi.entity.Vendas;

public interface VendasService {

    List<Vendas> listarVendas();
    Vendas save(Vendas vendas) ;
    List<Vendas> litarVendaPorCliente(String name) ;
    Optional<Vendas> findByIdOptional(Integer id) ;
    void delete(Integer id);

}