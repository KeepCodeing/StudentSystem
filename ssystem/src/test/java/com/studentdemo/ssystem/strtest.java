package com.studentdemo.ssystem;

import com.studentdemo.ssystem.POJO.CourseIdListPOJO;
import com.studentdemo.ssystem.POJO.CourseSelePOJO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class strtest {
    @Test
    public void t() {
        String str = "1";
        System.out.println(Integer.valueOf(str));
        Integer integer = Integer.valueOf(str);
//
//        System.out.println(integer + 1);
//        String string = "1-*/2-*/3-*/4-*/5-*/6-*/7-*/1-*/2-*/3-*/0-*/";
//        String[] split = string.split("/");
//        System.out.println("--"+split[split.length - 1]+"--");
//        for (String s : split) {
//            for (String r : s.split("-")) {
//                System.out.print(r + " ");
//            }
//            System.out.println();
//        }

    }
    @Test
    public void t2() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        String result = "";
        String l = "1-*/2-*/3-*/4-*/5-*/6-*/7-*/1-*/2-*/3-*/1-*/";
        // 然后再对其原选课列表进行操作
        String[] seleCourseInfoSplited = l.split("/");
        for (Integer id : list) {
            for (int i = 0; i < seleCourseInfoSplited.length; i++) {
                if (seleCourseInfoSplited[i].contains(String.valueOf(id))) {
                    seleCourseInfoSplited[i] = "";
                }
            }
        }
        Arrays.stream(seleCourseInfoSplited).forEach((s) -> System.out.println(s));
    }
}
