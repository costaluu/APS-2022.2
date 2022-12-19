# APS-2022.2

```
Humberto - hlfs2
Marcelo - mvbs3
Costa - jlcs3
Jonathan - jkss
```

## Escopo

```
O grupo se propõe a modelar uma aplicação de vendas online(E-Commerce) de propósito geral.
```

## Diagrama Casos de Uso

## Fluxos casos de uso
> - **Descrição**: Esse caso de uso é responsável por autenticar e criar sessões dentro da aplicação.
> - **Pré-condição**: Estar na tela de login.
> - **Pós-condição**: O sistema inicia uma nova sessão para o usuário
- ## **1. Login usuário**
    - 1.1 O cliente fornece login e senha
    - 1.2 O sistema verifica se o login e senha são válidos
    - 1.3 O sistema inicia o início da sessão desse usuário
> - **Fluxo secundário**: No passo 2, em caso das credenciais do usuário não forem válidas o sistema exibe uma mensagem de erro, volte ao passo 1.
----
> - **Descrição**: Esse caso de uso é responsável por autenticar e criar sessões dentro da aplicação.
> - **Pré-condição**: Estar logado.
> - **Pós-condição**: TODO.
- ## **2. Publicar item**
    - 2.1 O anunciante está na tela de publicar item
    - 2.2 O anunciante preenche as informações para a publicação
    - 2.3 Realizar publicação
> - **Fluxo secundário**: TODO.
----
> - **Descrição**: Esse caso de uso é responsável por autenticar e criar sessões dentro da aplicação.
> - **Pré-condição**: Estar logado.
> - **Pós-condição**: TODO.
- ## **3. Remover item**
    - 3.1 O anunciante na página de remover item
    - 3.1 Remover publicação
> - **Fluxo secundário**: TODO.
----
> - **Descrição**: Esse caso de uso é responsável pela criação de pedidos na conta do usuário.
> - **Pré-condição**: Estar logado e na tela de carrinho de compras.
> - **Pós-condição**: O pedido é cadastrado na conta do usuário
- ## **4. Realizar pagamento(CARTÃO DE CRÉDITO)**
    - 4.1 O sistema verifica a disponibilidade dos produtos solicitados
    - 4.2 O usuário fornece informações do endereço de entrega.
        - CEP
        - Número
    - 4.3 O usuário fornece as informações de pagamento
        - Número do cartão
        - Código de segurança
        - Validade
        - Nome do cartão
    - 4.4 O sistema envia o pagamento à operadora de cartão de crédito.
    - 4.5 O sistema confirma a transação e adiciona o pedido a conta do usuário.
> - **Fluxo secundário**: No passo 1, em caso de não disponibilidade o sistema mostra uma mensagem de erro. o sistema remove o item do carrinho e volta ao passo 1. No passo 4, caso o pagamento não seja confirmado o sistema cancela a operação.
----
> - **Descrição**: Esse caso de uso é responsável pela criação de pedidos na conta do usuário.
> - **Pré-condição**: Estar logado e na tela de carrinho de compras.
> - **Pós-condição**: O pedido é cadastrado na conta do usuário
- ## **5. Realizar pagamento(PIX)**
    - 5.1 O sistema verifica a disponibilidade dos produtos solicitados
    - 5.2 O usuário fornece informações do endereço de entrega.
        - CEP
        - Número
    - 5.3 O sistema gera o código PIX(copia e cola)
    - 5.4 O sistema espera 24h para a efetuação do pagamento.
    - 5.5 O sistema confirma a transação e adiciona o pedido a conta do usuário.
> - **Fluxo secundário**: - No passo 1, em caso de não disponibilidade o sistema mostra uma mensagem de erro. o sistema remove o item do carrinho e volta ao passo 1. No passo 4, caso o pagamento não seja confirmado o sistema cancela a operação.
