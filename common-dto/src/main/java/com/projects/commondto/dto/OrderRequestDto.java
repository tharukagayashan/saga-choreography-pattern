package com.projects.commondto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private String userId;
    private String productId;
    private Float amount;
    private Integer orderId;
}
