package com.linyuang.www.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.linyuang.www.po.KnowledgePoint;
import com.linyuang.www.service.KnowledgePointService;

import java.util.ArrayList;
import java.util.List;

public class KnowledgePointListener extends AnalysisEventListener<KnowledgePoint> {
    private KnowledgePointService knowledgePointService;
    private List<KnowledgePoint> knowledgePointList;
    public KnowledgePointListener(KnowledgePointService knowledgePointService){
        this.knowledgePointService = knowledgePointService;
    }
    @Override
    public void invoke(KnowledgePoint knowledgePoint, AnalysisContext analysisContext) {
        if(knowledgePointList == null){
            knowledgePointList = new ArrayList<>();
        }
        System.out.println(knowledgePoint);
        knowledgePointList.add(knowledgePoint);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //每个sheet读取完都会执行该方法，所以读取完空白的sheet后list是空的，不能调用业务层方法对list进行处理，所以要先判断list是否为空
        if(knowledgePointList == null){
            return;
        }
        knowledgePointService.dealKnowledgePoint(knowledgePointList);
        knowledgePointList = null;
    }
}
