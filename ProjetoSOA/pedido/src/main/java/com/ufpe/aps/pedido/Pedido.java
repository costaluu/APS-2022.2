package com.ufpe.aps.pedido;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "pedido")
public class Pedido {

    @Id
    private Integer idPedido;

    private Double valorDaCompra;

    private String loginCliente;

    private String statusPedido;
}
