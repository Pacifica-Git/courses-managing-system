package com.linyuang.www.service;

import com.linyuang.www.po.KnowledgePoint;
import com.linyuang.www.vo.QueryVo;

import java.util.List;

public interface KnowledgePointService {
    /**
     *获取知识点数据进行展示
     * @param
     * @return java.util.List<com.linyuang.www.po.KnowledgePoint>
     */
    List<KnowledgePoint> showKnowledgePoint(QueryVo queryVo);
    /**
     *将解析得到的知识点集合进行处理
     * @param knowledgePointList
     * @return void
     */
    void dealKnowledgePoint(List<KnowledgePoint> knowledgePointList);
}
