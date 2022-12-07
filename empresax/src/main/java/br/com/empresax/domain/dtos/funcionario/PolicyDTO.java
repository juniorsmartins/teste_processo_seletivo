package br.com.empresax.domain.dtos.funcionario;

public sealed interface PolicyDTO<ID> permits GerenteDTORequest, GerenteDTOResponse, SecretarioDTORequest,
        SecretarioDTOResponse, VendedorDTORequest, VendedorDTOResponse { }
