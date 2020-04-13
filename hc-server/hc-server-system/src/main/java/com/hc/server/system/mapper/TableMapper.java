package com.hc.server.system.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TableMapper {
    @Select("select table_name AS value from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<Map> listTable();

    @Select("select column_name AS columnName from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) AND column_key = 'pri' and TABLE_NAME=#{tableName}")
    String tableId(String tableName);
}
