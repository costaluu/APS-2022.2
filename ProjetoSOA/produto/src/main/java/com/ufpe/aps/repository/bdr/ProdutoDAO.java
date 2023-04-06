package com.ufpe.aps.repository.bdr;

import com.ufpe.aps.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoDAO extends JpaRepository<Produto, String> {
    List<Produto> findByDono(String dono);
}
