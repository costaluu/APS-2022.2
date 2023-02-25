package com.ufpe.aps.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    private Double valorDaCompra;

    private String loginCliente;

    @Id
    private Integer idPedido;

    private String statusPedido;
}
