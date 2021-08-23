package com.teentech.hotels.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CleanDto implements Serializable
{
    private long hotelId;

    private long roomNumber;

    private Date lastCleanDay;

    private Date lastChangeLingerie;

    private Date lastChangeTowels;
}
