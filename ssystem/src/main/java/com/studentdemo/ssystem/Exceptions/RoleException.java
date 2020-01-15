package com.studentdemo.ssystem.Exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleException extends Exception {
    private Integer code;
    private String msg;
    public RoleException(String msg, Integer code) {
        this.code = code;
        this.msg = msg;
    }
}
