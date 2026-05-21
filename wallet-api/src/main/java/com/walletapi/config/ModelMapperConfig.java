package com.walletapi.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class ModelMapperConfig {

    @Produces
    @ApplicationScoped
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Posteriormente as configurações customizadas ModelMapper antigo,
        // irei coloque-las aqui antes do return.
        // Exe: mapper.getConfiguration().setSkipNullEnabled(true);

        return mapper;
    }
}
