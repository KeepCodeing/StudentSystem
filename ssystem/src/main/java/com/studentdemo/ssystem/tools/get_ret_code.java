package com.studentdemo.ssystem.tools;

import com.studentdemo.ssystem.Security.JsonResponse;


public class get_ret_code {
    public static JsonResponse response(String msg, Integer code) {
        return new JsonResponse(msg, code);
    }
}
