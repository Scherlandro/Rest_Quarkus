package com.walletapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "itensdavenda")
@AllArgsConstructor
@NoArgsConstructor
public class ItensDaVenda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_itens_vd", length = 20)
    private Integer idItensVd;

    @Column(name = "codevendas", length = 20)
    private Integer codVenda;

    @Column(name = "cod_produtos",length = 20)
    private String codProduto;

    @Column(length = 60)
    private String descricao;

    @Column(name = "valor_compra")
    private Double valCompra;

    @Column(name = "valor_venda")
    private Double valVenda;

    @Column(name = "qtd_vendidas",length = 11)
    private Integer qtdVendidas;

    @Column(name = "valor_parcial")
    private Double valorParcial;

    @Transient
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dtRegistro;


}
