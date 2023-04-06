package com.ufpe.aps;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.ufpe.aps.conta.Conta;
import com.ufpe.aps.gui.*;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import com.ufpe.aps.pojo.PagamentoDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WireMockTest(httpPort = 8081)
class EcommerceContaTests {

	@Autowired
	private TelaCadastrarPresenter telaCadastrarPresenter;

	@Autowired
	private TelaCheckoutPresenter telaCheckoutPresenter;

	@Autowired
	private TelaProdutoPresenter telaProdutoPresenter;

	@Autowired
	private TelaPublicarItemPresenter telaPublicarItemPresenter;

	@Autowired
	private TelaDeLoginPresenter telaDeLoginPresenter;

	@BeforeAll
	public static void setup(){

	}

	@AfterAll
	public static void tearDown(){

	}

	@Test
	@Order(1)
	void contextLoads() {
		assertThat(telaCadastrarPresenter).isNotNull();
		assertThat(telaCheckoutPresenter).isNotNull();
		assertThat(telaProdutoPresenter).isNotNull();
		assertThat(telaPublicarItemPresenter).isNotNull();
		assertThat(telaDeLoginPresenter).isNotNull();
	}

	@Test
	@Order(2)
	void testarCadastroOk() {
		Conta conta = getConta();
		assertThat(telaCadastrarPresenter.efetuarCadastro(conta)).isEqualTo(ResponseEntity.ok().build());
	}

	@Test
	@Order(3)
	void testarCadastroNaoOk() {
		Conta conta = getConta();
		telaCadastrarPresenter.efetuarCadastro(conta);
		assertThat(telaCadastrarPresenter.efetuarCadastro(conta))
				.isEqualTo(ResponseEntity.status(HttpStatus.CONFLICT).body("Conta já cadastrada"));
	}

	@Test
	@Order(4)
	void testarLoginOk() {
		Conta conta = getConta();
		telaCadastrarPresenter.efetuarCadastro(conta);
		assertThat(telaDeLoginPresenter.efetuarLogin(conta)).isEqualTo(ResponseEntity.ok().build());
	}

	@Test
	@Order(5)
	void testarAddProdutoCarrinhoOk(){
		AddProdutoCarrinhoDTO produtoDTO = getAddProduto();

		assertThat(telaProdutoPresenter.addProdutoCarrinho(produtoDTO)).isEqualTo(ResponseEntity.ok().build());
//		fachada.addProdutoCarrinho(produtoDTO);
	}

	@Test
	@Order(6)
	void testarAddProdutoCarrinhoNaoExistente(){
		AddProdutoCarrinhoDTO produtoDTO = getAddProduto();
		produtoDTO.setIdProduto("teste2");
		assertThat(telaProdutoPresenter.addProdutoCarrinho(produtoDTO))
				.isEqualTo(ResponseEntity.status(HttpStatus.FORBIDDEN).body("Produto e/ou quantidade não disponível no estoque"));
	}

	@Test
	@Order(7)
	void testarAddProdutoCarrinhoQuantidadeMaiorQueOEstoque(){
		AddProdutoCarrinhoDTO produtoDTO = getAddProduto();
		produtoDTO.setQuantidade(100);
		assertThat(telaProdutoPresenter.addProdutoCarrinho(produtoDTO))
				.isEqualTo(ResponseEntity.status(HttpStatus.FORBIDDEN).body("Quantidade de produtos no carrinho maior que a quantidade disponível no estoque"));
	}

	@Test
	@Order(8)
	void testarCheckoutOk(){
		stubFor(get(urlEqualTo("/pagamento"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "text/plain")
						.withBody("OK")));

		PagamentoDTO pagamentoDTO = getPagamentoDTO();
		assertThat(telaCheckoutPresenter.efetuarPagamento(pagamentoDTO)).isEqualTo(ResponseEntity.ok().build());
	}


	@Test
	@Order(9)
	void testarCheckoutNaoAutorizado(){
		stubFor(get(urlEqualTo("/pagamento"))
				.willReturn(aResponse()
						.withStatus(401)
						.withHeader("Content-Type", "text/plain")
						.withBody("Não autorizado")));
		PagamentoDTO pagamentoDTO = getPagamentoDTO();
		assertThat(telaCheckoutPresenter.efetuarPagamento(pagamentoDTO))
				.isEqualTo(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401 Pagamento não autorizado"));
	}



	private Conta getConta() {
		Conta conta = new Conta();
		conta.setLogin("teste");
		conta.setSenha("teste");
		return conta;
	}

	private AddProdutoCarrinhoDTO getAddProduto() {
		AddProdutoCarrinhoDTO produtoDTO = new AddProdutoCarrinhoDTO();
		produtoDTO.setLogin("teste");
		produtoDTO.setIdProduto("1");
		produtoDTO.setQuantidade(1);
		return produtoDTO;
	}

	private PagamentoDTO getPagamentoDTO() {
		PagamentoDTO pagamentoDTO = new PagamentoDTO();
		pagamentoDTO.setLogin("teste1");
		pagamentoDTO.setNomeNoCartao("123456789");
		pagamentoDTO.setNomeNoCartao("teste");
		pagamentoDTO.setValidade("12/2020");
		pagamentoDTO.setCodSeguranca(123);
		return pagamentoDTO;
	}

}
