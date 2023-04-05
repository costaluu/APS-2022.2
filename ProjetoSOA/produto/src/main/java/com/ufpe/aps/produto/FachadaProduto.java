package com.ufpe.aps.produto;

import com.ufpe.aps.factory.impl.FabricaRepositorioBDR;
import com.ufpe.aps.factory.impl.FabricaRepositoriosInMemory;
import com.ufpe.aps.repository.bdr.ProdutoDAO;
import org.springframework.core.env.Environment;

public class FachadaProduto {

//    private final IRegistroProduto registroProduto;
//
//    private final IServicoProduto servicoProduto;
//
//    public FachadaProduto(Environment env, ProdutoDAO repository) {
//        String choice = env.getProperty("fabrica.repositorios.choice") != null ? env.getProperty("fabrica.repositorios.choice") : "INMEMORY";
//        assert choice != null;
//        IRepositorioProduto repo = choice.equalsIgnoreCase("bdr") ?
//                new FabricaRepositorioBDR(repository).criarRepositorioProduto() : new FabricaRepositoriosInMemory().criarRepositorioProduto();
//        this.registroProduto = new RegistroProduto(repo);
//    }

}
