package com.studentdemo.ssystem.Security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "用来存放JSON返回数据的类")
@Data
public class JsonResponse {

    @ApiModelProperty(value = "返回信息")
    private String message;

    @ApiModelProperty(value = "返回代码")
    private Integer code;

    public JsonResponse() {
    }

    public JsonResponse(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
