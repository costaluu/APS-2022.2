package com.ufpe.aps.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufpe.aps.carrinho.Carrinho;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ContaCarrinhoConverter implements AttributeConverter<Carrinho, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Carrinho attribute) {
        if(attribute == null) return null;
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Carrinho convertToEntityAttribute(String dbData) {
        if(dbData == null) return null;
        Carrinho carrinho = null;
        try {
            carrinho = objectMapper.readValue(dbData, Carrinho.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return carrinho;
    }
}
