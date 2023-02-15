# APS-2022.2

```
Humberto - hlfs2
Marcelo - mvbs3
Costa - jlcs3
Jonathan - jkss
```

## Escopo


O grupo se propõe a modelar uma aplicação de vendas online(E-Commerce) de propósito geral. Nessa aplicação, os clientes poderão se registrar e logar sendo usuários, adicionar produtos em seus carrinhos, efetuar a compra dos produtos no carrinho, avaliar os produtos e buscar produtos.

# Diagrama Casos de Uso!
![diagrama](https://user-images.githubusercontent.com/44981765/219095835-7bb1813b-d53c-47bd-a9fb-1a94772329df.PNG)


# Fluxos casos de uso

## **1. Login**
<details>
<summary>Expandir</summary>
<p>

> - **Descrição**: Esse caso de uso é responsável por autenticar e criar sessões dentro da aplicação.
> - **Pré-condição**: Estar na tela de login.
> - **Pós-condição**: O sistema inicia uma nova sessão para o usuário

### **Fluxo**
1. O cliente fornece email e senha
2. O sistema verifica se o email e senha são válidos
3. O sistema inicia a sessão desse usuário

> - **Fluxo secundário**: No passo 2, em caso das credenciais do usuário não forem válidas o sistema exibe uma mensagem de erro, volte ao passo 1. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

----

<br>

## **2. Cadastro**
<details>
<summary>Expandir</summary>
<p>

> - **Descrição**: Esse caso de uso é responsável por autenticar e criar usuários dentro da aplicação.
> - **Pré-condição**: Estar na tela de cadastro.
> - **Pós-condição**: O sistema cria um novo usuário.

### **Fluxo**
1. O cliente fornece email e senha
2. O sistema verifica se o email e senha estão cadastrados
3. O sistema insere o usuário no banco de dados.

> - **Fluxo secundário**: No passo 2, em caso das credenciais do usuário existirem o sistema exibe uma mensagem de erro, volte ao passo 1. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

----

<br>

## **3. Publicar item**
<details>
<summary>Expandir</summary>
<p>

> - **Descrição**: Esse caso de uso é responsável por adicionar um item para venda a conta do usuário logado.
> - **Pré-condição**: Estar logado e na tela de publicar item.
> - **Pós-condição**: Um item é cadastrado a conta do usuário.
### **Fluxo**
1. O anunciante preenche as informações para a publicação
    - Nome
    - Descrição
    - Preço
    - Foto
2. O sistema valida as informações preenchidas.
3. O sistema mostra uma mensagem de confirmação.
> - **Fluxo secundário**: No passo 2, em caso de alguma informação não ser válida o sistema mostra uma mensagem de erro e volta ao passo 1. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

----

<br>

## **4. Adicionar item ao carrinho**
<details>
<summary>Expandir</summary>
<p>

> - **Descrição**: Esse caso de uso é responsável por adicionar itens ao carrinho do usuário.
> - **Pré-condição**: Estar logado e na tela de um produto.
> - **Pós-condição**: Um item é adicionado ao carrinho do usuário.
### **Fluxo**
1. O usuário insere a quantidade desejada
2. O sistema verifica a disponibilidade do produto solicitado
> - **Fluxo secundário**: No passo 2, em caso de não disponibilidade o sistema mostra uma mensagem de erro e volta ao passo 1.

</p>
</details>

<br>

----

<br>

## **5. Checkout (CARTÃO)**
<details>
<summary>Expandir</summary>
<p>

> - **Descrição**: Esse caso de uso é responsável pela criação de pedidos na conta do usuário, usando a operadora de cartão de crédito.
> - **Pré-condição**: Estar logado e na tela de carrinho de compras.
> - **Pós-condição**: O pedido é cadastrado na conta do usuário
## **Fluxo**
1. O usuário fornece informações do endereço de entrega.
    - CEP
    - Número
2. O usuário fornece as informações de pagamento
    - Número do cartão
    - Código de segurança
    - Validade
    - Nome do cartão
3. O sistema envia o pagamento à operadora de cartão de crédito.
4. O sistema confirma a transação e adiciona o pedido a conta do usuário.

> - **Fluxo secundário**: No passo 4, caso o pagamento não seja confirmado o sistema cancela a operação. O usuário pode cancelar a qualquer momento.

</p>
</details>

<br>

----

<br>

## **6. Checkout (PIX)**
<details>
<summary>Expandir</summary>
<p>

> - **Descrição**: Esse caso de uso é responsável pela criação de pedidos na conta do usuário, usando uma API do PIX.
> - **Pré-condição**: Estar logado e na tela de carrinho de compras.
> - **Pós-condição**: O pedido é cadastrado na conta do usuário
## **Fluxo**
1. O usuário fornece informações do endereço de entrega.
    - CEP
    - Número
2. O sistema consulta a API e gera o código PIX(copia e cola)
3. O sistema espera 24h para a efetuação do pagamento.
4. O sistema confirma a transação e adiciona o pedido a conta do usuário.

> - **Fluxo secundário**: No passo 4, caso o pagamento não seja confirmado o sistema cancela a operação. O usuário pode cancelar a qualquer momento. No passo 3 caso a requisição para a API falhe uma mensagem de erro é mostrada ao usuário.

</p>
</details>

<br>

----

<br>

## **7. Avaliar produto**
<details>
<summary>Expandir</summary>
<p>

> - **Descrição**: Esse caso de uso é responsável pela criação de avaliações de produtos.
> - **Pré-condição**: Estar logado, estar na tela de um produto já comprado.
> - **Pós-condição**: A avaliação é vinculada ao produto.
## **Fluxo**
1. O usuário fornece uma avaliação.
    - Nota (0 a 5)
    - Comentário
2. O sistema checa se o usuário comprou aquele produto.
3. O sistema mostra uma mensgem de confirmação e vincula a avaliação ao produto.

> - **Fluxo secundário**: No passo 2, caso o campo Comentário estiver vazio, uma mensagem de erro será mostrada ao usuário.

</p>
</details>

<br>

----

<br>
