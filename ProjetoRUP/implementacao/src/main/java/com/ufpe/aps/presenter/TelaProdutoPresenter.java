package com.ufpe.aps.presenter;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.fachada.Fachada;
import com.ufpe.aps.pojo.AddProdutoCarrinhoDTO;
import com.ufpe.aps.repository.interfaces.IRepositorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${carrinho.servlet.path}")
public class TelaProdutoPresenter {

    @Autowired
    Fachada fachada;

    @PostMapping("/adicionar")
    public ResponseEntity adicionar(@RequestBody AddProdutoCarrinhoDTO produtoDTO) {
        try{
            fachada.addProdutoCarrinho(produtoDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
