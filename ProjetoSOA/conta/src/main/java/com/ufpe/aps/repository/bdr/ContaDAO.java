package com.ufpe.aps.repository.bdr;

import com.ufpe.aps.conta.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaDAO extends JpaRepository<Conta, String> {
}
