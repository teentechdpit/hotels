package com.teentech.hotels.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CleanDto implements Serializable
{
    private long hotelId;

    private long roomNumber;

    private Date lastCleanDay;

    private Date lastChangeLingerie;

    private Date lastChangeTowels;
}
