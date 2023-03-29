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

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@WireMockTest(httpPort = 8081)
class EcommerceApplicationTests {
}
