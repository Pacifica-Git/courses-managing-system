package com.linyuang.www.mapper;

import com.linyuang.www.po.SystemCourse;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemCourseDao {
    /**
     *新增知识点集合课程
     * @param systemCourse 要新增的知识点集合课程
     * @return int
     */
    public int insertSystemCourse(SystemCourse systemCourse);
}
