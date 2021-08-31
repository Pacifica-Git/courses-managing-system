package com.linyuang.www.mapper;

import com.linyuang.www.po.KnowledgePoint;
import com.linyuang.www.vo.QueryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface KnowledgePointDao {
    /**
     *更新知识点数据
     * @param knowledgePoint 要被更新的知识点
     */
    void updateKnowledgePoint(KnowledgePoint knowledgePoint);
    /**
     *插入新的知识点
     * @param knowledgePoint 要被新增的知识点
     */
    void insertKnowledgePoint(KnowledgePoint knowledgePoint);
    /**
     *根据指定的排序字段，排序规则进行分页查询数据，分页功能由pageHelper实现
     * @param queryVo 查询条件
     * @return java.util.List<com.linyuang.www.po.KnowledgePoint>
     */
    List<KnowledgePoint> showKnowledge(QueryVo queryVo);
    /**
     *根据知识点的id
     * @param knowledgePointId 知识点的id
     * @return com.linyuang.www.po.KnowledgePoint
     */
    KnowledgePoint findKnowledgePointById(Integer knowledgePointId);
    /**
     *查找并返回所有知识点
     * @param
     * @return java.util.List<com.linyuang.www.po.KnowledgePoint>
     */
    List<KnowledgePoint> findAllKnowledgePoint();
    /**
     *删除知识点
     * @param knowledgePointId 要被删除的知识点的id
     * @return void
     */
    void deleteKnowledgePoint(Integer knowledgePointId);
}
