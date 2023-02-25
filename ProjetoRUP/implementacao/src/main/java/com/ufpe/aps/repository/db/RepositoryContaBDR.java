package com.ufpe.aps.repository.db;

import com.ufpe.aps.entity.Conta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryContaBDR extends CrudRepository<Conta, String> {
}
