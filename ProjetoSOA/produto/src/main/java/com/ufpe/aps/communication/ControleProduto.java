package com.ufpe.aps.communication;

import com.ufpe.aps.carrinho.Carrinho;
import com.ufpe.aps.produto.ControladorProduto;
import com.ufpe.aps.exception.ProdutoNotFoundException;
import com.ufpe.aps.exception.QuantidadeProdutoException;
import com.ufpe.aps.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("${produto.servlet.path}")
public class ControleProduto {

    private final ControladorProduto controladorProduto;

    @Autowired
    public ControleProduto(ControladorProduto controladorProduto) {
        this.controladorProduto = controladorProduto;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.ok(controladorProduto.pegarTodosProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") String id,
                                                  @RequestParam Integer quantidade)
            throws ProdutoNotFoundException, QuantidadeProdutoException {
        if(quantidade == null || quantidade <= 0)
            throw new QuantidadeProdutoException("Quantidade do produto inválida ou não informada");

        return ResponseEntity.ok(controladorProduto.pegarProduto(id, quantidade));
    }

    @PostMapping("/publicar")
    public ResponseEntity<Produto> publicarProduto(@RequestBody Produto produto) {
        try{
            controladorProduto.publicarItem(produto);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/atualiza-estoque")
    public ResponseEntity<Void> atualizaEstoque(@RequestBody Carrinho carrinho) {
        try{
            controladorProduto.atualizaEstoque(carrinho);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
