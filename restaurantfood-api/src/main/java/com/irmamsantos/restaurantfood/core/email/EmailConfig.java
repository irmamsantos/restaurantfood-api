package com.irmamsantos.restaurantfood.core.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irmamsantos.restaurantfood.domain.service.EnvioEmailService;
import com.irmamsantos.restaurantfood.infrastructure.service.email.FakeEnvioEmailService;
import com.irmamsantos.restaurantfood.infrastructure.service.email.SandboxEnvioEmailService;
import com.irmamsantos.restaurantfood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {
        // Acho melhor usar switch aqui do que if/else if
        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandboxEnvioEmailService();
            default:
            	//??
                return null;
        }
    }
}