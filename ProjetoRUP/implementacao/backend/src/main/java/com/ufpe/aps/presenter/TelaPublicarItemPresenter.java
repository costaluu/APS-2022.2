package com.ufpe.aps.presenter;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.fachada.Fachada;
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
