package com.linyuang.www.service.impl;

import com.github.pagehelper.PageHelper;
import com.linyuang.www.mapper.KnowledgePointDao;
import com.linyuang.www.po.KnowledgePoint;
import com.linyuang.www.service.KnowledgePointService;
import com.linyuang.www.vo.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lenovo
 */
@Service
@Slf4j
public class KnowledgePointServiceImpl implements KnowledgePointService {
    @Autowired
    private KnowledgePointDao knowledgePointDao;
    @Override
    public List<KnowledgePoint> showKnowledgePoint(QueryVo queryVo) {
        Integer pageNumber = queryVo.getPageNum();
        PageHelper.startPage(pageNumber,10);
        log.info("已查询第[{}]页数据",queryVo.getPageNum());
        return knowledgePointDao.showKnowledge(queryVo);
    }

    @Override
    public void dealKnowledgePoint(List<KnowledgePoint> knowledgePointList) {
        knowledgePointList.forEach(knowledgePoint -> {
            KnowledgePoint selectedKnowledgePoint = knowledgePointDao.findKnowledgePointById(knowledgePoint.getId());
            if(selectedKnowledgePoint == null){
                knowledgePointDao.insertKnowledgePoint(knowledgePoint);
                log.info("新增知识点[{}]",knowledgePoint.getId());
            }else{
                if(selectedKnowledgePoint.getCourse() == knowledgePoint.getCourse().intValue()){
                    knowledgePointDao.updateKnowledgePoint(knowledgePoint);
                    log.info("已更新知识点[{}]的信息",knowledgePoint.getId());
                }else{
                    throw new IllegalArgumentException(knowledgePoint.getId() + "该知识点的所属课程出错");
                }
            }
        });
        List<KnowledgePoint> allKnowledgePoint = knowledgePointDao.findAllKnowledgePoint();
        allKnowledgePoint.forEach(knowledgePoint -> {
            if(!knowledgePointList.contains(knowledgePoint)){
                knowledgePointDao.deleteKnowledgePoint(knowledgePoint.getId());
                log.info("已删除知识点[{}]", knowledgePoint.getId());
            }
        });
    }
}
