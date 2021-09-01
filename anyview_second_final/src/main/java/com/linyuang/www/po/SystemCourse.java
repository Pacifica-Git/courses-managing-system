package com.linyuang.www.po;

import lombok.Data;

@Data
public class SystemCourse {
    private int id;
    private String name;
    private String introduction;

    public SystemCourse(Integer systemCourseId, String systemCourseName) {

    }
}
