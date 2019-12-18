package com.stackroute.vehicledemand.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timeslotbooking")
public class DateDemand {
    private String date;
    private TimeSlot timeSlot1;
    private TimeSlot timeSlot2;
    private TimeSlot timeSlot3;

    public DateDemand(String date, TimeSlot timeslot1, TimeSlot timeslot2, TimeSlot timeslot3) {
        this.date = date;
        this.timeSlot1 = timeslot1;
        this.timeSlot2 = timeslot2;
        this.timeSlot3 = timeslot3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TimeSlot getTimeslot1() {
        return timeSlot1;
    }

    public void setTimeslot1(TimeSlot timeslot1) {
        this.timeSlot1 = timeslot1;
    }

    public TimeSlot getTimeslot2() {
        return timeSlot2;
    }

    public void setTimeslot2(TimeSlot timeslot2) {
        this.timeSlot2 = timeslot2;
    }

    public TimeSlot getTimeslot3() {
        return timeSlot3;
    }

    public void setTimeslot3(TimeSlot timeslot3) {
        this.timeSlot3 = timeslot3;
    }
}
