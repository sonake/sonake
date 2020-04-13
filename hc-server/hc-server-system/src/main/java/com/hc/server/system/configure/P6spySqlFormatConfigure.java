package com.hc.server.system.configure;

import com.hc.common.utils.DateUtil;
import com.hc.common.utils.CommonTools;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 9:29
 * @description：p6spy打印sql
 * @version: 1.0
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return CommonTools.isNotEmpty(sql) ? DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN)
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
    }
}
