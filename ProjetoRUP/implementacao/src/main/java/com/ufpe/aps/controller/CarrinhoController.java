package com.ufpe.aps.controller;

//import com.ufpe.aps.controlador.CadastroProduto;
import com.ufpe.aps.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("${carrinho.servlet.path}")
public class CarrinhoController {

//    @Autowired
//    CadastroProduto cadastroProduto;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
//        return new ResponseEntity<>(cadastroProduto.getAllProdutos(), HttpStatus.OK);
        return null;
    }

    @PostMapping
    public ResponseEntity<Map> saveProduto(@RequestBody Produto produto) {
//        cadastroProduto.createProduto(produto);
//        return new ResponseEntity(Map.of("message", "Criado com sucesso"), HttpStatus.OK);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> deleteProduto(@PathVariable("id") String idProduto){
//        cadastroProduto.deleteProduto(idProduto);
//        return new ResponseEntity<>(Map.of("message", "Produto deletado com sucesso"), HttpStatus.OK);
        return null;
    }
}
