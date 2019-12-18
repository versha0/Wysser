package com.stackroute.vehicledemand.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "retailerdemand")
public class retailerdemand {
    @Id
    private String volume;
    private String timeSlot;
    private Date date;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public retailerdemand(String volume, String timeSlot, Date date) {
        this.volume = volume;
        this.timeSlot = timeSlot;
        this.date = date;
    }

    public retailerdemand() {
    }
}
