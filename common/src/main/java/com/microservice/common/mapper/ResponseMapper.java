package com.microservice.common.mapper;


import com.microservice.common.http.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
public class ResponseMapper {
    public <T> Response toResponse(T dto , String message, String dtoType, HttpStatus status) {
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .message(message)
                .data(Map.of(dtoType, dto != null ? dto : "No data to display"))
                .status(status)
                .statusCode(status.value())
                .build();
    }
}
