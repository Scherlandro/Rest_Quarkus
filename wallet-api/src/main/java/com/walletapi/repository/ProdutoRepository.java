package com.walletapi.repository;

import com.walletapi.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepositoryBase<Produto, Integer> {

    public List<Produto> listarProdutoPorNome(String nome) {
        return find("trim(nomeProduto) like ?1", nome + "%").list();
    }
}
