package com.ufpe.aps.repository.mongo;

import com.ufpe.aps.conta.RegistroConta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaMongoRepository extends MongoRepository<RegistroConta, String> {
}
