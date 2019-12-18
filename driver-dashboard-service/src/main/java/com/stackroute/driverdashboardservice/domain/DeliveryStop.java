package com.stackroute.driverdashboardservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryStop {
    private int stopNumber;
    private double longitude;
    private double latitude;
    private int orderId;
}
