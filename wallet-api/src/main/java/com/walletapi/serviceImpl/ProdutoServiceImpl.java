package com.walletapi.serviceImpl;

import com.walletapi.entity.Produto;
import com.walletapi.repository.ProdutoRepository;
import com.walletapi.service.ProdutoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    ProdutoRepository repository;

    @Override
    public List<Produto> listarProduto() {
        return repository.listAll();
    }

    @Override
    @Transactional
    public Produto save(Produto produto) {
        repository.persist(produto);
        return produto;
    }

    @Override
    public List<Produto> listarProdutoPorNome(String nome) {
        return repository.listarProdutoPorNome(nome);
    }

    @Override
    public Optional<Produto> litarProdutoPorCod(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Produto> findById(Integer id) {
        return repository.findByIdOptional(id);
    }

    @Override
    public Boolean existsById(Integer id) {
        return null;
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}