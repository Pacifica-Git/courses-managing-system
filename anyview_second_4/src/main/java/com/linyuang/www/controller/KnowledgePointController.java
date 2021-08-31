package com.linyuang.www.controller;

import com.alibaba.excel.EasyExcel;
import com.linyuang.www.listener.KnowledgePointListener;
import com.linyuang.www.po.KnowledgePoint;
import com.linyuang.www.po.ResultDto;
import com.linyuang.www.service.KnowledgePointService;
import com.linyuang.www.util.FileUtil;
import com.linyuang.www.util.ZipUtil;
import com.linyuang.www.vo.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class KnowledgePointController {
    @Autowired
    private KnowledgePointService knowledgePointService;
    private ObjectMapper objectMapper;

    /**
     *展示数据
     * @param queryVo 查询条件，包括查询页码，排序字段，排序规则
     * @return void
     */
    @RequestMapping("/show")
    public ResultDto showData(QueryVo queryVo) throws IOException {
        List<KnowledgePoint> knowledgePoints = knowledgePointService.showKnowledgePoint(queryVo);
        return new ResultDto("数据查询成功",objectMapper.writeValueAsString(knowledgePoints));
    }

    @RequestMapping(value = "/import")
    public ResultDto analyseExcel(@RequestParam("systemCourseId") Integer systemCourseId, @RequestParam("systemCourseName") String systemCourseName, MultipartFile zipFile, String targetName) throws IOException {
        String zipFileName = FileUtil.upload(zipFile);
        ZipUtil.unZip(zipFileName,targetName);
        EasyExcel.read(FileUtil.getProjectFilePath(targetName),KnowledgePoint.class,new KnowledgePointListener(knowledgePointService)).doReadAll();
        return new ResultDto(200,"文件解析成功");
    }
}
