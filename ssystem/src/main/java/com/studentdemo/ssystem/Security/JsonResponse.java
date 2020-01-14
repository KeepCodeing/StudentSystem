package com.studentdemo.ssystem.Security;

import lombok.Data;

@Data
public class JsonResponse {
    private String message;
    private Integer code;

    public JsonResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }


}
