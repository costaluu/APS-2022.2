package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.produto.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoMongoRepository extends MongoRepository<Produto, String> {
}
