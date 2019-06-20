package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hc.admin.bean.HcExc;
import com.hc.admin.bean.Menu;
import com.hc.admin.bean.Role;
import com.hc.admin.bean.User;
import com.hc.admin.common.utils.HcEnum;
import com.hc.admin.common.utils.HttpContextUtil;
import com.hc.admin.dao.HcExcDao;
import com.hc.admin.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private HcExcDao hcExcDao;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public User getUser(String username) throws Exception {
        String userString = this.redisService.get(HcEnum.USER_CACHE_PREFIX.getValue() + username);
        if (StringUtils.isBlank(userString)) {
            throw new Exception();
        } else {
            return this.mapper.readValue(userString, User.class);
        }
    }

    @Override
    public List<Role> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(HcEnum.USER_ROLE_CACHE_PREFIX.getValue() + username);
        if (StringUtils.isBlank(roleListString)) {
            throw new Exception();
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Role.class);
            return this.mapper.readValue(roleListString, type);
        }
    }

    @Override
    public List<Menu> getPermissions(String username) throws Exception {
        String permissionListString = this.redisService.get(HcEnum.USER_PERMISSION_CACHE_PREFIX.getValue() + username);
        if (StringUtils.isBlank(permissionListString)) {
            throw new Exception();
        } else {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Menu.class);
            return this.mapper.readValue(permissionListString, type);
        }
    }

//    @Override
//    public UserConfig getUserConfig(String userId) throws Exception {
//        String userConfigString = this.redisService.get(FebsConstant.USER_CONFIG_CACHE_PREFIX + userId);
//        if (StringUtils.isBlank(userConfigString))
//            throw new Exception();
//        else
//            return this.mapper.readValue(userConfigString, UserConfig.class);
//    }

    @Override
    public void saveUser(User user) throws Exception {
        String username = user.getUserName();
        this.deleteUser(username);
        redisService.set(HcEnum.USER_CACHE_PREFIX.getValue() + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        User user = userService.findByName(username);
        this.deleteUser(username);
        redisService.set(HcEnum.USER_CACHE_PREFIX.getValue() + username, mapper.writeValueAsString(user));
    }



    @Override
    public void saveRoles(String username) throws Exception {
        List<Role> roleList = this.roleService.findUserRole(username);
        if (!roleList.isEmpty()) {
            this.deleteRoles(username);
            redisService.set(HcEnum.USER_ROLE_CACHE_PREFIX.getValue() + username, mapper.writeValueAsString(roleList));
        }

    }

    @Override
    public void savePermissions(String username) throws Exception {
        List<Menu> permissionList = this.menuService.findUserPermissions(username);
        if (!permissionList.isEmpty()) {
            this.deletePermissions(username);
            redisService.set(HcEnum.USER_PERMISSION_CACHE_PREFIX.getValue() + username, mapper.writeValueAsString(permissionList));
        }
    }

//    @Override
//    public void saveUserConfigs(String userId) throws Exception {
//        UserConfig userConfig = this.userConfigService.findByUserId(userId);
//        if (userConfig != null) {
//            this.deleteUserConfigs(userId);
//            redisService.set(FebsConstant.USER_CONFIG_CACHE_PREFIX + userId, mapper.writeValueAsString(userConfig));
//        }
//    }

    @Override
    public void deleteUser(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(HcEnum.USER_CACHE_PREFIX.getValue() + username);
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(HcEnum.USER_ROLE_CACHE_PREFIX.getValue() + username);
    }

    @Override
    public void deletePermissions(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(HcEnum.USER_PERMISSION_CACHE_PREFIX.getValue() + username);
    }

    /**
     * 将所有的异常信息放进缓存里
     * @throws Exception
     */
    @Override
    public void saveExcMsg() throws Exception {
        List<HcExc> list=hcExcDao.selectList(new LambdaQueryWrapper<HcExc>());
        if (!list.isEmpty()) {
                this.deleteExc(HcEnum.Hc_EXC.getValue());
                for (HcExc he:list) {
                    redisService.set(HcEnum.Hc_EXC.getValue()+"."+he.getClassname()+"."+he.getFieldname(),he.getCode()+":"+he.getMsg());
                }
        }

    }
    @Override
    public void deleteExc(String code) throws Exception{
        redisService.del(HcEnum.Hc_EXC.getValue()+code);
    }



//    @Override
//    public void deleteUserConfigs(String userId) throws Exception {
//        redisService.del(HcEnum.USER_CONFIG_CACHE_PREFIX. + userId);
//    }
}
