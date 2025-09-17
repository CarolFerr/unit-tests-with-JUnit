package org.example.barriga.domain;

import org.example.barriga.domain.exceptions.ValidationException;

import java.util.Objects;

public class Conta {

    private Long id;
    private String nome;
    private Usuario usuario;

    public Conta(Long id, String nome, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;

        if(nome == null){throw new ValidationException("Nome é obrigatório");}
        if(usuario == null){throw new ValidationException("Usuário é obrigatório");}
    }

    public Conta() {

    }


    public Long id() {
        return id;
    }


    public String nome() {
        return nome;
    }

    public Usuario usuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        // Checking primitive arguments e and Objects(in case Usuario)
        return Objects.equals(id, conta.id) && Objects.equals(nome, conta.nome) && Objects.equals(usuario, conta.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
