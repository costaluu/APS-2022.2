package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.entity.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaMongoRepository extends MongoRepository<Conta, String> {
}
