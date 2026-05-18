package com.walletapi.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walletapi.dtos.ItensDaVendaDto;
import com.walletapi.entity.ItensDaVenda;
import com.walletapi.repository.ItensDaVendaRepository;
import com.walletapi.service.ItensDaVendaService;

import jakarta.transaction.Transactional;

@Service
public class ItenDaVendaServiceImpl implements ItensDaVendaService {


   final ItensDaVendaRepository itensDaVendaRepository;

    public ItenDaVendaServiceImpl(ItensDaVendaRepository repository) {

        this.itensDaVendaRepository = repository;
    }


    @Transactional
    public ItensDaVenda save(ItensDaVenda itensDaVenda) {
        itensDaVendaRepository.persist(itensDaVenda);
        return itensDaVenda;

    }

    @Override
    public List<ItensDaVendaDto> findAll() {
        return itensDaVendaRepository.findAllItens();
    }

    @Override
    public ItensDaVenda findById(Integer id) {
        return itensDaVendaRepository.findById(id);
    }

    @Override
    public List<ItensDaVendaDto> listarItensDaVdPorId(Integer id){

        return itensDaVendaRepository.findItensVdById(id);
    }

    @Override
    public List<ItensDaVendaDto> ConsultarItensVdEntreDatas(String dtIni, String dtFinal) {
        return itensDaVendaRepository.litarItemDaVendaEntreData(dtIni, dtFinal);
    }

    @Override
    public List<ItensDaVendaDto> litarItemDaVendaPorData(String dt) {
        return itensDaVendaRepository.litarItemDaVendaPorData(dt);
    }

 @Override
    public List<ItensDaVendaDto> litarItemDaVendaPorCliente(String nome) {
        return itensDaVendaRepository.litarItemDaVendaPorCliente(nome);
    }

}
