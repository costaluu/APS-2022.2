package com.ufpe.aps.gui;

import com.ufpe.aps.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("${produto.servlet.path}")
public class TelaPublicarItemPresenter {

    private final Fachada fachada;

    @Autowired
    public TelaPublicarItemPresenter(Fachada fachada) {
        this.fachada = fachada;
    }

    @PostMapping("/publicar")
    public void publicar(@RequestBody Produto itemDTO) {
        fachada.publicarItem(itemDTO);
    }
}
