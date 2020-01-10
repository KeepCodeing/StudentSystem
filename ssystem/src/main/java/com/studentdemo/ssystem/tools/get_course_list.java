package com.studentdemo.ssystem.tools;

import java.util.List;

public class get_course_list {
    static public String ret_list(String bef_str, List<Integer> course_list) {
        String result = bef_str;
        for (Integer i : course_list) {
            result += (i + "-*/");
        }
        return result;
    }
}
