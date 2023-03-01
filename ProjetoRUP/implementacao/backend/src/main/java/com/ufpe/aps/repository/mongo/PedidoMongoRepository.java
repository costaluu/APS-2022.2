package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.entity.Conta;
import com.ufpe.aps.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoMongoRepository extends MongoRepository<Pedido, String> {
}
