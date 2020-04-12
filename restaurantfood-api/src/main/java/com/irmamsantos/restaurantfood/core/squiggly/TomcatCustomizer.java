package com.irmamsantos.restaurantfood.core.squiggly;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

//O tomcat não aceita por omissão [ ] no url do request, teria usar os encoding para %5B e %5D
//Para configurar o Tomcar para aceitar [ ] no url do request têm de fazer esta configuração
//existente nesta classe
@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[]"));
    }
    
}