package com.micropos.delivery;

import com.micropos.delivery.dto.DeliveryDto;
import com.micropos.delivery.dto.OrderDto;
import com.micropos.delivery.model.Delivery;
import com.micropos.delivery.model.Order;
import com.micropos.delivery.service.DeliveryService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.Resource;
import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.micropos.delivery.mapper")
public class DeliveryApplication {
    private static final Logger log = LoggerFactory.getLogger(DeliveryApplication.class);

    @Resource
    private StreamBridge streamBridge;

    @Resource
    DeliveryService deliveryService;

    public static void main(String[] args) {
        //SpringApplication.run(DeliveryApplication.class, args);
        SpringApplication application = new SpringApplication(DeliveryApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    Consumer<Order> create() {
        return order -> {
            DeliveryDto deliveryDto = new DeliveryDto();
            deliveryDto.setOrderId(order.getId());
            deliveryDto.setStatus(DeliveryDto.StatusEnum.CREATED);
            deliveryService.createDelivery(deliveryDto);
        };
    }
}
