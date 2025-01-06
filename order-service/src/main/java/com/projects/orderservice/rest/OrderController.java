package com.projects.orderservice.rest;

import com.projects.commondto.dto.OrderRequestDto;
import com.projects.orderservice.model.PurchaseOrder;
import com.projects.orderservice.service.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final PurchaseOrderService orderService;

    public OrderController(PurchaseOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping("/all")
    public List<PurchaseOrder> getAllOrders() {
        return orderService.getAllOrders();
    }

}
