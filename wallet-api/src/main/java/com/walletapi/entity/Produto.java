package com.walletapi.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "produto")
public class Produto extends PanacheEntityBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    public Integer idProduto;

    @Column(length = 20, name = "cod_produto")
    public String codProduto;

    @Column(length = 20, name = "codDofabricante")
    public String codDoFabricante;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "inicio_cadastro")
    public LocalDate dtCadastro; // Alterado para LocalDate

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "ultima_atualizacao")
    public LocalDate ultimaAtualizacao; // Alterado para LocalDate

    @Column(length = 60, name = "nome_produto")
    public String nomeProduto;

    @Column(length = 45, name = "corDoproduto")
    public String corDoProduto;

    @Column(length = 4, name = "unidadeMedidas")
    public String unidadeMedidas;

    @Column(length = 300)
    public String obs;

    @Column(name = "valor_compra")
    public Double valorCompra;

    public Double percentual;

    @Column(name = "valor_venda")
    public Double valorVenda;

    @Column(name = "quantidade_estoque")
    public Integer qtdEstoque;

    @Column(name = "estoque_minimo")
    public Integer estoqueMinimo;

    @Column(name = "qtd_vendidas")
    public Integer qtdVendidas;

    @Column(name = "id_fornecedor")
    public Integer idFornecedor;

    @Column(name = "id_marca")
    public Integer idMarca;

    @Column(name = "id_modelo")
    public Integer idModelo;


    @Column(name = "foto_produto", columnDefinition = "VARBINARY(MAX)")
    public byte[] fotoProduto; // ou o tipo que você estiver usando (Blob, etc)


}