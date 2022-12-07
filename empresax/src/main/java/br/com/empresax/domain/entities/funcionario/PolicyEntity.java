package br.com.empresax.domain.entities.funcionario;

public sealed interface PolicyEntity<ID> permits Gerente, Secretario, Vendedor { }
