package com.linyuang.www.po;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 知识点实体类
 * @author Lenovo
 */
@Data
public class KnowledgePoint implements Serializable {
    //@JsonIgnore
    @ExcelProperty("编号(id)")
    private Integer id;
    @ExcelProperty("名称(name)")
    private String name;
    @ExcelProperty("所属知识点集合（set）")
    private Integer course;
    @ExcelProperty("所属章（chapter）")
    private Integer chapter;
    @ExcelProperty("所属节(section)")
    private Integer section;
    @ExcelProperty("定义（definition）")
    private String definition;
    @ExcelProperty("别名（another_name，允许空）")
    private String anotherName;
    @ExcelProperty("要求掌握程度(Required_of_ mastery)")
    private Integer requiredOfMastery;
    @ExcelProperty("难度(degree_of_difficulty)")
    private Integer degreeOfDifficulty;
    @ExcelProperty("关联题目数量（前端显示,Number_of_related_questions）")
    private Integer numberOfRelatedQuestions;
    @ExcelProperty("备注（remark，允许空）")
    private String remark;
}
