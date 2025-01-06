package com.projects.orderservice.service;

import com.projects.commondto.dto.OrderRequestDto;
import com.projects.commondto.event.OrderStatus;
import com.projects.orderservice.dao.PurchaseOrderRepository;
import com.projects.orderservice.model.PurchaseOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository orderRepository;
    private final OrderServicePublisher orderServicePublisher;

    public PurchaseOrderService(PurchaseOrderRepository orderRepository, OrderServicePublisher orderServicePublisher) {
        this.orderRepository = orderRepository;
        this.orderServicePublisher = orderServicePublisher;
    }

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        PurchaseOrder order = orderRepository.save(convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(order.getOrderId());

        //Produce kafka event with status ORDER_CREATED
        orderServicePublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return order;
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    private PurchaseOrder convertDtoToEntity(OrderRequestDto orderRequestDto) {
        PurchaseOrder order = new PurchaseOrder();
        order.setOrderId(orderRequestDto.getOrderId());
        order.setUserId(orderRequestDto.getUserId());
        order.setProductId(orderRequestDto.getProductId());
        order.setPrice(orderRequestDto.getAmount());
        order.setOrderStatus(OrderStatus.ORDER_CREATED);
        return order;
    }

}