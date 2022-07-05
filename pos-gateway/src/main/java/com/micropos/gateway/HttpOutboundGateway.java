package com.micropos.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.webflux.dsl.WebFlux;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HttpOutboundGateway {
    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows.from("pos_channel")
                .handle(WebFlux.outboundGateway(message ->
                                UriComponentsBuilder.fromUriString("http://localhost:8088/api/delivery/byorder/{orderId}")
                                        .buildAndExpand(message.getPayload())
                                        .toUri())
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(DeliveryDto.class))
                .get();
    }
}
