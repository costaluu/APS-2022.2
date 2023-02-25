package com.ufpe.aps.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conta")
@Getter
@Setter
@NoArgsConstructor
public class Conta {
    @Id
    private String login;
    private String senha;
}
