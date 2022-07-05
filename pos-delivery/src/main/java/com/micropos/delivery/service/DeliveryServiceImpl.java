package com.micropos.delivery.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.micropos.delivery.mapper.DeliveryMapper;
import com.micropos.delivery.dto.DeliveryDto;
import com.micropos.delivery.model.Delivery;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Component
public class DeliveryServiceImpl implements DeliveryService {

    @Resource
    private DeliveryMapper deliveryMapper;


    @Override
    public Mono<DeliveryDto> findDeliveryByOrderId(Long orderId) {
        Delivery  delivery = deliveryMapper.selectOne(new QueryWrapper<Delivery>().eq("order_id", orderId));
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        deliveryDto.setOrderId(delivery.getOrderId());
        deliveryDto.setStatus(DeliveryDto.StatusEnum.valueOf(delivery.getStatus()));
        return Mono.just(deliveryDto);
    }

    @Override
    public Mono<DeliveryDto> getDeliveryById(Long deliveryId) {
        Delivery  delivery = deliveryMapper.selectById(deliveryId);
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        deliveryDto.setOrderId(delivery.getOrderId());
        deliveryDto.setStatus(DeliveryDto.StatusEnum.valueOf(delivery.getStatus()));
        return Mono.just(deliveryDto);
    }

    @Override
    public Mono<Long> createDelivery(DeliveryDto delivery) {
        Delivery delivery1 = new Delivery();
        delivery1.setOrderId(delivery.getOrderId());
        delivery1.setStatus(delivery.getStatus().name());
        deliveryMapper.insert(delivery1);
        return Mono.just(delivery1.getId());
    }


}
