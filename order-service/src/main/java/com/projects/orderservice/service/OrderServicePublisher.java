package com.projects.orderservice.service;

import com.projects.commondto.dto.OrderRequestDto;
import com.projects.commondto.event.OrderEvent;
import com.projects.commondto.event.OrderStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderServicePublisher {

    private final Sinks.Many<OrderEvent> orderSinks;

    public OrderServicePublisher(Sinks.Many<OrderEvent> orderSinks) {
        this.orderSinks = orderSinks;
    }

    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }

}