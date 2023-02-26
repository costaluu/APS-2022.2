package com.ufpe.aps.presenter;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${item.servlet.path}")
public class TelaProdutoPresenter {

    @Autowired
    Fachada fachada;

    @PostMapping("/adicionar")
    public void adicionar(@RequestBody Produto produtoDTO) {
        fachada.addProdutoCarrinho(produtoDTO);
    }
}
