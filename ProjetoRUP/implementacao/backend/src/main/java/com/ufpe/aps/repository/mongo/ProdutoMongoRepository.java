package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.entity.Pedido;
import com.ufpe.aps.entity.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoMongoRepository extends MongoRepository<Produto, String> {
}
