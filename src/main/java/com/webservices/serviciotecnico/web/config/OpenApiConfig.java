package com.webservices.serviciotecnico.web.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicScheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic")))
                .info(new Info()
                        .title("API de Sistema de Servicio TÃ©cnico")
                        .version("1.0")
                        .description("Esta es la documentaciÃ³n oficial de los servicios web para el Sistema de GestiÃ³n de Servicio TÃ©cnico. " +
                                     "AquÃ­ podrÃ¡s probar los endpoints de usuarios, roles, personas, tipos, equipos y Ã³rdenes de servicio.")
                        .contact(new Contact()
                                .name("Soporte TÃ©cnico")
                                .email("soporte@tusistema.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
