package com.amber.common.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@ApiModel(description = "Æû³µÏà¹Ø")
public class Car {

    private String make;

    private int numberOfSeats;

//    private CarType type;
}
