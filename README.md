# APS-2022.2

```
Humberto - hlfs2
Marcelo - mvbs3
Costa - jlcs3
Jonathan - jkss
```

## Apresentações

**SOA**: https://docs.google.com/presentation/d/1kzoT4_xYsk3v77pDi594pU5Yfk6BKGEc-saNCi3AHdk/edit#slide=id.g1dd8eb9a3b8_7_75

## Escopo

O grupo se propõe a modelar uma aplicação de vendas online (E-Commerce) de propósito geral. Nessa aplicação, serão expostos produtos em geral que clientes queiram vender e possivelmente comprar, os clientes poderão se registrar e logar sendo usuários, adicionar produtos em seus carrinhos, efetuar a compra dos produtos no carrinho utilizando um agente externo, nesse caso, cartão de crédito.

# Diagrama Casos de Uso!!

![Diagrama](https://user-images.githubusercontent.com/31044920/221423971-89de05e3-b815-44a2-95c2-1cc88663672c.png)

# Fluxos casos de uso

## **1. Login**

<details>
<summary>Expandir</summary>
<p>

> -   **Descrição**: Esse caso de uso é responsável por autenticar e criar sessões dentro da aplicação.
> -   **Pré-condição**: Estar na tela de login.
> -   **Pós-condição**: O sistema inicia uma nova sessão para o usuário

### **Fluxo**

1. O cliente fornece email e senha
2. O sistema verifica se o email e senha são válidos
3. O sistema inicia a sessão desse usuário

> -   **Fluxo secundário**: No passo 2, em caso das credenciais do usuário não forem válidas o sistema exibe uma mensagem de erro, volte ao passo 1. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

---

<br>

## **2. Cadastro**

<details>
<summary>Expandir</summary>
<p>

> -   **Descrição**: Esse caso de uso é responsável por autenticar e criar usuários dentro da aplicação.
> -   **Pré-condição**: Estar na tela de cadastro.
> -   **Pós-condição**: O sistema cria um novo usuário.

### **Fluxo**

1. O cliente fornece email e senha
2. O sistema verifica se o email e senha estão cadastrados
3. O sistema insere o usuário no banco de dados.

> -   **Fluxo secundário**: No passo 2, em caso das credenciais do usuário existirem o sistema exibe uma mensagem de erro, volte ao passo 1. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

---

<br>

## **3. Publicar item**

<details>
<summary>Expandir</summary>
<p>

> -   **Descrição**: Esse caso de uso é responsável por adicionar um item para venda a conta do usuário logado.
> -   **Pré-condição**: Estar logado e na tela de publicar item.
> -   **Pós-condição**: Um item é cadastrado a conta do usuário.

### **Fluxo**

1. O anunciante preenche as informações para a publicação
    - Nome
    - Descrição
    - Preço
    - Quantidade
    - Foto
2. O sistema valida as informações preenchidas.
3. O sistema mostra uma mensagem de confirmação.
    > - **Fluxo secundário**: No passo 2, em caso de alguma informação não ser válida o sistema mostra uma mensagem de erro e volta ao passo 1. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

---

<br>

## **4. Adicionar item ao carrinho**

<details>
<summary>Expandir</summary>
<p>

> -   **Descrição**: Esse caso de uso é responsável por adicionar itens ao carrinho do usuário.
> -   **Pré-condição**: Estar logado e na tela de um produto.
> -   **Pós-condição**: Um item é adicionado ao carrinho do usuário.

### **Fluxo**

1. O usuário insere a quantidade desejada
2. O sistema verifica a disponibilidade do produto solicitado
    > - **Fluxo secundário**: No passo 2, em caso de não disponibilidade o sistema mostra uma mensagem de erro e volta ao passo 1.

</p>
</details>

<br>

---

<br>

## **5. Checkout**

<details>
<summary>Expandir</summary>
<p>

> -   **Descrição**: Esse caso de uso é responsável pela criação de pedidos na conta do usuário, usando a operadora de cartão de crédito.
> -   **Pré-condição**: Estar logado e na tela de carrinho de compras.
> -   **Pós-condição**: O pedido é cadastrado na conta do usuário

## **Fluxo**

<!-- 1. O usuário fornece informações do endereço de entrega.
    - CEP
    - Número -->

1. O usuário fornece as informações de pagamento
    - Número do cartão
    - Código de segurança
    - Validade
    - Nome do cartão
2. O sistema envia o pagamento à operadora de cartão de crédito.
3. O sistema confirma a transação e adiciona o pedido a conta do usuário.

> -   **Fluxo secundário**: No passo 4, caso o pagamento não seja confirmado o sistema cancela a operação. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

---

<br>
