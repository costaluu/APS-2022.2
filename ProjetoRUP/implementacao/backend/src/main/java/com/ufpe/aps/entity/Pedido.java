package com.ufpe.aps.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    private Integer idPedido;

    private Double valorDaCompra;

    private String loginCliente;

    private String statusPedido;
}
