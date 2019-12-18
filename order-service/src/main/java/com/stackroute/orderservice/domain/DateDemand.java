package com.stackroute.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateDemand {
    private String date;
    private TimeSlot timeslot1;
    private TimeSlot timeslot2;
    private TimeSlot timeslot3;
}
