package com.studentdemo.ssystem.Enum;

public enum CodeEnum {
    // Java 中枚举类的写法和C/C++完全不一样，它可以提供方法
    // 但是要求枚举对象必须写在枚举类的最前面

    // 用户操作成功 1 ~ 200
    SUCCESS_LOGIN("登陆成功", 1),
    SUCCESS_REG("注册成功", 2),
    SUCCESS_SET_ROLE("创建角色成功", 3),

    // 用户操作失败 201 ~ 400
    FAIL_LOGIN("登陆失败", 201),
    FAIL_REG("注册失败", 202),
    FAIL_SET_ROLE("创建角色失败", 203),
    FAIL_ACCESS("用户权限不足", 204),

    // 用户未登录 0
    USER_NO_LOGIN("用户未登录", 0);

    private String msg;

    private Integer code;


    CodeEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
