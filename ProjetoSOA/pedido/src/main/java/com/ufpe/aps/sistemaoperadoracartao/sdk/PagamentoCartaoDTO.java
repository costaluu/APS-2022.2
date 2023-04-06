package com.ufpe.aps.sistemaoperadoracartao.sdk;

import com.ufpe.aps.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoCartaoDTO {
    private String login;
    private String numCartao;
    private int codSeguranca;
    private String validade;
    private String nomeNoCartao;
    private Pedido meuPedido;

}
