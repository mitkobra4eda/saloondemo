package com.ddimitko.prototype.objects;

import jdk.jfr.Timestamp;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;
import org.threeten.extra.Interval;

import javax.persistence.*;
import java.time.*;

@Getter
@Setter
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long serviceId;

    private Long shopId;

    private String staffId;

    private Long userId;

    private String staffName;

    private String userName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

}
