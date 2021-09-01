package com.linyuang.www.service.impl;

import com.linyuang.www.mapper.SystemCourseDao;
import com.linyuang.www.po.SystemCourse;
import com.linyuang.www.service.SystemCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class SystemCourseServiceImpl implements SystemCourseService {
    @Autowired
    private SystemCourseDao systemCourseDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int dealSystemCourse(Integer systemCourseId, String systemCourseName) {
        if(systemCourseId == null){
            systemCourseDao.insertSystemCourse(new SystemCourse(systemCourseId,systemCourseName));
            log.info("已新增知识点集合课程[{}]",systemCourseName + systemCourseId);
            return 1;
        }else{
            return -1;
        }
    }
}
