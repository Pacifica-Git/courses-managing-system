package com.linyuang.www.service;

public interface SystemCourseService {
    /**
     *判断知识点集合课程是更新或是新增
     * @param systemCourseId 知识点集合课程的id
     * @param systemCourseName 知识点集合课程的名称
     * @return int 1,表示新增成功
     *              -1，表示是更新
     */
    int dealSystemCourse(Integer systemCourseId, String systemCourseName);
}
