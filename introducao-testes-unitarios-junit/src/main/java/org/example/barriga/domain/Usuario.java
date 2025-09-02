package org.example.barriga.domain;
import org.example.barriga.domain.exceptions.ValidationException;

import java.util.Map;

// Simple POJO (Plain Old Java Object) class representing a User entity
// It encapsulates user attributes and provides a constructor and getter methods
// This object is immutable after creation, as there are no setter methods
// This design is useful for ensuring that user data remains consistent and unchanged after instantiation

public class Usuario {
    private final Long id;
    private final String nome;
    private final String email;
    private final String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        validar(Map.of(
                "Nome", nome,
                "Email", email,
                "Senha", senha
        ));
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    private void validar(Map<String, String> campos) {
        campos.forEach((campo, valor) -> {
            if (valor == null) throw new ValidationException(campo + " é obrigatório");
        });
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
}




