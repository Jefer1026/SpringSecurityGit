package org.jog.springsecuritygit.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ApiError implements Serializable {

    @Serial
    private static final long serialVersionUID = -1304062615664320477L;

    private String backendMessage;
    private String message;
    private String url;
    private String httpMethod;
    private LocalDateTime timeStamp;
}
