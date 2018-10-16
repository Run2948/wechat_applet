package com.platform.entity;

/**
 * 名称：ResultMap <br>
 * 描述：查询表信息返回的BaseResultMap<br>
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-09-17 20:20
 */
public class ResultMap {
    /**
     * 数据库字段名
     */
    private String columnName;
    /**
     * 字段类型
     */
    private String dataType;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 主键
     */
    private String columnKey;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }
}
