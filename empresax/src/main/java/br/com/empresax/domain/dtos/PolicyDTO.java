package br.com.empresax.domain.dtos;

public sealed interface PolicyDTO<ID> permits GerenteDTORequest, GerenteDTOResponse, SecretarioDTORequest,
        SecretarioDTOResponse, VendedorDTORequest, VendedorDTOResponse { }
