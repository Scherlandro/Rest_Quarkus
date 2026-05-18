package com.walletapi.service;

import java.util.List;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.entity.ItensDaVenda;


public interface ItensDaVendaService {

    List<ItensDaVendaDto> findAll();

    ItensDaVenda findById(Integer id);

    List<ItensDaVendaDto> listarItensDaVdPorId(Integer id);

    List<ItensDaVendaDto> ConsultarItensVdEntreDatas(String dtIni, String dtFinal);

     List<ItensDaVendaDto> litarItemDaVendaPorData(String dt);

     List<ItensDaVendaDto> litarItemDaVendaPorCliente(String nome);

    ItensDaVenda save(ItensDaVenda itensDaVenda);
}
