package com.teentech.hotels.dto;

import lombok.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {

    private String to;
    private List<String> cc;
    private String subject;
    private String content;
    private ByteArrayOutputStream attachmentFile;

}
