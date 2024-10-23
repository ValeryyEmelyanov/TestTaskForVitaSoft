package com.example.testtaskforvitasoftapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
    private Long id;
    private String text;
    private Long userId;
    public String getFormattedText() {
        return text.replaceAll(".", "$0-").replaceAll("-$", "");
    }
}
