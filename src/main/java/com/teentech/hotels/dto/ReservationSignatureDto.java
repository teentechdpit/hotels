package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationSignatureDto extends ReservationDto {

    private String signature;
}
