package com.projects.commondto.dto;

import com.projects.commondto.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private String userId;
    private String productId;
    private Float amount;
    private Integer orderId;
    private OrderStatus orderStatus;
}
