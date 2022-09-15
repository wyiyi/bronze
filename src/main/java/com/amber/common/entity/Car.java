package com.amber.common.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ApiModel(description = "�������")
public class Car {

    private String make;

    private int numberOfSeats;

//    private CarType type;
}
