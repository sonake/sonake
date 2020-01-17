package com.hc.server.system.mapper;

import com.hc.common.bean.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-12-31 17:58:07
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> findPermsByUserId(@RequestParam("userId") Long userId);
}
