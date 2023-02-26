package com.ufpe.aps.presenter;

import com.ufpe.aps.entity.Produto;
import com.ufpe.aps.fachada.Fachada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${produto.servlet.path}")
public class TelaPublicarItemPresenter {

    @Autowired
    Fachada fachada;

    @PostMapping("/publicar")
    public void publicar(@RequestBody Produto itemDTO) {
        fachada.publicarItem(itemDTO);
    }
}
