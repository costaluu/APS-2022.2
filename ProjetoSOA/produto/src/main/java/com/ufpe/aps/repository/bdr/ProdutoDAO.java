package com.ufpe.aps.repository.bdr;

import com.ufpe.aps.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDAO extends JpaRepository<Produto, String> {
}
